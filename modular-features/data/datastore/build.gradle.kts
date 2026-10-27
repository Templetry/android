plugins {
    alias(libs.plugins.buildlogic.data)
    alias(libs.plugins.buildlogic.datastore)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "es.sebas1705.data.datastore"
    // protobuf-gradle-plugin 0.10 registers the generated Java/Kotlin sources with AGP itself.
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.26.1"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    api(projects.data.analytics)
}