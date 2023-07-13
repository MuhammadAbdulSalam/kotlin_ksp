import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.register
import java.io.File

class SnapItPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.register<Copy>("snapItGenerate") {
            val packagePath = File("${project.buildDir}/snapIt", "snap_config.txt").readText()
            dependsOn("assembleDebug")
            from("build/generated/ksp/debug/kotlin/com/adbsalam/snapit/")
            into("src/test/java/$packagePath")
            filter { line -> line.replace("//", "") }
        }
    }

}
