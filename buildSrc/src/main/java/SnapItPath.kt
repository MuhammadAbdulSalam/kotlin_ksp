import org.gradle.api.Project
import java.io.File

const val snapItConfigFile = "snap_config.txt"
const val srcTestPath = "src/test/java/"
const val snapIt = "snapIt"

fun snapIt(func: SnapItPath.() -> Unit) = SnapItPath().apply { func() }

class SnapItPath {

    fun Project.testDir(path: String) {
        val location = path.replace(srcTestPath, "")
        val directory = "${this.buildDir}"
        var buildDir = File(directory)
        if (!buildDir.exists()) {
            buildDir.mkdir()
        }
        File(directory, snapIt).mkdir()
        File("$directory/$snapIt", snapItConfigFile).printWriter().use { writer ->
            writer.write(location)
        }
    }
}
