import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.register
import java.io.File

internal fun Project.snapItGenerateTask() {

    val snapConfigFile = File("${this.buildDir}/snapIt", "snap_config.txt")

    this.tasks.register<Copy>("snapItGenerate") {

        dependsOn("assemble")

        if (!snapConfigFile.exists()) {
            throw (Exception(
                """property snapItTestDestination { <TEST SOURCESET LOCATION> } missing from module build.gradle
                    | Please add snapItTestDestination { <TEST SOURCESET LOCATION> } to your module build.gradle
                    | 
                    | Example: If test location needs to be in src/test/java/com/example/tests 
                    | In module build.gradle add following property
                    | snapItTestDestination {
                    |   "com/example/tests"
                    | }
                    | 
                    | Once property added please try a refresh and build
                """.trimMargin()
            ))
        }

        val packagePath = snapConfigFile.readText()

        from("build/generated/ksp/debug/kotlin/com/adbsalam/snapit/")
        into("src/test/java/$packagePath")
        filter { line -> line.replace("//", "") }
    }
}