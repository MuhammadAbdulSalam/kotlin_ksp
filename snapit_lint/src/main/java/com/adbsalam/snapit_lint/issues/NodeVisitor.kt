package com.adbsalam.snapit_lint.issues

import com.adbsalam.snapit_lint.helper.errors
import com.adbsalam.snapit_lint.helper.hasSnapIt
import com.adbsalam.snapit_lint.issues.SnapItIssues.ISSUE
import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.JavaContext
import org.jetbrains.uast.UMethod

class NodeVisitor(
    private val context: JavaContext
) : UElementHandler() {

    override fun visitMethod(node: UMethod) {

        if (node.annotations.isEmpty()) return

        if (!node.hasSnapIt()) return

        val lintError = node.errors() ?: return

        context.report(
            issue = ISSUE,
            scopeClass = node,
            location = context.getNameLocation(node),
            message = lintError.msg
        )
    }
}

