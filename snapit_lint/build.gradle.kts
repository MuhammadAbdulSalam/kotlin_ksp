plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    compileOnly(libs.kotlin.std.libs)
    compileOnly(libs.lint.api)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.jar {
    manifest.attributes["Lint-Registry-v2"] = "com.adbsalam.snapit_lint.SnapItIssueRegistry"
}
