import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

internal fun Project.snapItDependencies() {
    this.dependencies {
        "implementation"(project(":annotations"))
        "ksp"(project(":processor"))
        "lintChecks"(project(":snapit_lint"))
        "testImplementation"(project(":testing"))
    }
}