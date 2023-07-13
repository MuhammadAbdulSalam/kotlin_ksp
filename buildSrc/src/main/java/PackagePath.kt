import org.gradle.api.Project
import java.io.File
import java.lang.System.out

const val testDestination = "snap_test_destination"

fun Project.snapItTestDestination(packagePath: () -> String) {
    val path = packagePath().replace("src/test/java/", "")
    val directory = "${this.buildDir}"
    File(directory, "snapIt").mkdir()
    File("$directory/snapIt", "snap_config.txt").printWriter().use { writer ->
        writer.write(path)
    }
}