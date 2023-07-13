plugins {
    `kotlin-dsl`
}

//dependencies {
//    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.21")
//    implementation("com.android.tools.build:gradle:3.5.3")
//}

gradlePlugin {
    plugins {
        create("SnapItPlugin") {
            id = "SnapItPlugin"
            implementationClass = "SnapItPlugin"
        }
    }
}