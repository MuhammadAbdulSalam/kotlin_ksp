plugins {
    `kotlin-dsl`
    alias(libs.plugins.kotlin.ksp)
    id("com.gradle.plugin-publish") version "1.1.0"
}

version = "1.0.0"
group = "com.adbsalam.snapit"

gradlePlugin {
    website.set("https://github.com/MuhammadAbdulSalam/kotlin_ksp")
    plugins {
        create("SnapIt") {
            id = "com.adbsalam.snapit"
            displayName = "SnapIt allows easy snapshot testing for compose"
            description = "An extension to Paparazzi testing, allows easy snapshot creation via @SnapIt annotation"
            tags.set(listOf("testing", "snapShotTesting", "PaparazziTesting"))
            implementationClass = "com.adbsalam.snapit.SnapIt"
        }
    }
}

publishing {
    repositories {
        maven {
            name = "snapit_local"
            url = uri("/Users/muhammadabdulsalam/Dev")
        }
    }
}
