package es.sebas1705.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor
import java.io.File
import java.util.Properties

/**
 * Enum class that represents the different dimensions of the flavors.
 *
 * @since 0.1.0
 * @author Sebas1705 01/03/2025
 */
enum class FlavorDimension {
    CONTENT_TYPE
}

/**
 * The three environment profiles every Templetry catalog form ships
 * (ADR-0018), realized here as Android product flavors — the ecosystem's own
 * mechanism, alongside `buildConfigField`.
 *
 * `environment` is the canonical lowercase name every profile reports on
 * `BuildConfig.ENVIRONMENT`, kept separate from the flavor's own identifier
 * (`Development`/`Staging`/`Production`, matching this project's existing
 * capitalized flavor style) so the two can diverge without either breaking.
 *
 * @since 0.1.0
 * @author Sebas1705 01/03/2025
 */
enum class CoreFlavor(
    val dimension: FlavorDimension,
    val environment: String,
    val applicationIdSuffix: String? = null,
    val verboseLogging: Boolean = false,
) {
    Development(FlavorDimension.CONTENT_TYPE, "development", ".dev", verboseLogging = true),
    Staging(FlavorDimension.CONTENT_TYPE, "staging", ".staging", verboseLogging = true),
    Production(FlavorDimension.CONTENT_TYPE, "production", ".pro", verboseLogging = false),
}

/**
 * Method that configures the flavors of each module and centralizes all the configuration in one place.
 *
 * Each flavor gets three `BuildConfig` fields: `ENVIRONMENT` (the canonical
 * name), `API_BASE_URL` (read from `app/secrets.properties`, since it is the
 * one value a developer plausibly wants to override locally) and
 * `VERBOSE_LOGGING` (fixed per flavor — production never ships it on).
 *
 * @since 0.1.0
 * @author Sebas1705 01/03/2025
 */
fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock : ProductFlavor.(flavor : CoreFlavor) -> Unit = {}
) {
    val secretsPropertiesFile = File("./app/secrets.properties")
    val secretsProperties = Properties()

    if (secretsPropertiesFile.exists()) {
        secretsPropertiesFile.inputStream().use { secretsProperties.load(it) }
    }

    commonExtension.apply {
        flavorDimensions.add(FlavorDimension.CONTENT_TYPE.name)
        CoreFlavor.values().forEach { coreFlavor ->
            productFlavors.create(coreFlavor.name) {
                dimension = coreFlavor.dimension.name
                flavorConfigurationBlock(coreFlavor)
                if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                    if (coreFlavor.applicationIdSuffix != null) {
                        applicationIdSuffix = coreFlavor.applicationIdSuffix
                    }
                }
                buildConfigField("String", "ENVIRONMENT", "\"${coreFlavor.environment}\"")
                buildConfigField(
                    "String",
                    "API_BASE_URL",
                    "\"${secretsProperties["API_BASE_URL_${coreFlavor.name.uppercase()}"] ?: ""}\""
                )
                buildConfigField("boolean", "VERBOSE_LOGGING", coreFlavor.verboseLogging.toString())
            }
        }
    }
}
