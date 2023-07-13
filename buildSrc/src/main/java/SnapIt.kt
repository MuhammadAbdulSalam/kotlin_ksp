import org.gradle.api.Plugin
import org.gradle.api.Project

class SnapIt : Plugin<Project> {

    override fun apply(project: Project) {

        project.snapItDependencies()

        project.snapItPlugins()

        project.snapItGenerateTask()
    }
}
