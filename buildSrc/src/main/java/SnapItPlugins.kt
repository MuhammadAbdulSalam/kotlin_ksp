import org.gradle.api.Project

internal fun Project.snapItPlugins() {
    this.pluginManager.apply("app.cash.paparazzi")
}