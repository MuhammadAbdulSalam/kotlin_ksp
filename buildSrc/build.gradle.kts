plugins {
    `kotlin-dsl`
    alias(libs.plugins.kotlin.ksp)
}

dependencies {
}

gradlePlugin {
    plugins {
        create("SnapItPlugin") {
            id = "SnapItPlugin"
            implementationClass = "SnapItPlugin"
        }
    }
}