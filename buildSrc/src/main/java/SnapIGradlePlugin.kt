import org.gradle.api.Plugin
import org.gradle.api.Project

class SnapItPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.create("SampleTask") {
            println("Hello there!")
        }
    }
}
