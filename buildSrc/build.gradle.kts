plugins {
    `kotlin-dsl`
    alias(libs.plugins.kotlin.ksp)
}

gradlePlugin {
    plugins {
        create("SnapIt") {
            id = "SnapIt"
            implementationClass = "SnapIt"
        }
    }
}
