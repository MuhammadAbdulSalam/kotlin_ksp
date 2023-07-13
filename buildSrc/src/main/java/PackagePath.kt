import org.gradle.api.Project
import java.io.File
import java.lang.System.out

const val snapItConfigFile = "snap_config.txt"
const val srcTestPath = "src/test/java/"
const val snapIt = "snapIt"

fun Project.snapItTestDestination(packagePath: () -> String) {
    val path = packagePath().replace(srcTestPath, "")
    val directory = "${this.buildDir}"
    var buildDir = File(directory)
    if (!buildDir.exists()) {
        buildDir.mkdir()
    }
    File(directory, snapIt).mkdir()
    File("$directory/$snapIt", snapItConfigFile).printWriter().use { writer ->
        writer.write(path)
    }
}