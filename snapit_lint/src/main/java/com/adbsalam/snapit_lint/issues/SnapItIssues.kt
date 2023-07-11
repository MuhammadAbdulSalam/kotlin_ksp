package com.adbsalam.snapit_lint.issues

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UMethod

object SnapItIssues {
    private const val ID = "SnapItValidation"
    private const val PRIORITY = 7
    private const val DESCRIPTION = "Following are the key rules to use SnapIt"
    private const val EXPLANATION = """
        - Methods must not be private
        - Methods must be @Composable
        - Cannot use @SnapIt and @ScreenSnapIt for same method
        - Fix these issues to proceed a build
    """
    private val CATEGORY = Category.CUSTOM_LINT_CHECKS

    private val SEVERITY = Severity.ERROR

    val ISSUE = Issue.create(
        ID,
        DESCRIPTION,
        EXPLANATION,
        CATEGORY,
        PRIORITY,
        SEVERITY,
        Implementation(SnapIssueDetector::class.java, Scope.JAVA_FILE_SCOPE)
    )

    class SnapIssueDetector : Detector(), Detector.UastScanner {
        override fun getApplicableUastTypes(): List<Class<out UElement>> =
            listOf(UMethod::class.java)

        override fun createUastHandler(context: JavaContext): UElementHandler =
            NodeVisitor(context)
    }
}