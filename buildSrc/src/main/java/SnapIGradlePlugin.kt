import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.register

class SnapItPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register<Copy>("SampleCopy") {
            dependsOn("assembleDebug")
            from("build/generated/ksp/debug/kotlin/com/adbsalam")
            into("src/test/java/com/adbsalam/greetings")
        }
    }
}
