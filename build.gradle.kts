buildscript {}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.kotlin) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.paparazzi) apply false
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}