plugins {
    `kotlin-dsl`
    alias(libs.plugins.kotlin.ksp)
}

version = "1.0.0"

gradlePlugin {
    plugins {
        create("SnapIt") {
            id = "SnapIt"
            implementationClass = "SnapIt"
        }
    }
}
