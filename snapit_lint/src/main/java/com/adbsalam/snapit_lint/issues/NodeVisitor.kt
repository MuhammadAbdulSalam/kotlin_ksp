package com.adbsalam.snapit_lint.issues

import com.adbsalam.snapit_lint.helper.COMPOSABLE
import com.adbsalam.snapit_lint.helper.SNAP_IT
import com.adbsalam.snapit_lint.helper.byPackage
import com.adbsalam.snapit_lint.issues.SnapItIssues.ISSUE
import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.JavaContext
import com.intellij.lang.jvm.JvmModifier
import org.jetbrains.uast.UMethod

class NodeVisitor(private val context: JavaContext) : UElementHandler() {

    override fun visitMethod(node: UMethod) {

        if (node.annotations.isEmpty()) return

        val isSnapIt = node.annotations.firstOrNull {
            it.qualifiedName == SNAP_IT.byPackage()
        } != null

        val isComposable =
            node.annotations.firstOrNull { it.qualifiedName == COMPOSABLE } != null

        val hasParams = node.hasParameters()

        val isPrivate = node.hasModifier(JvmModifier.PRIVATE)

        if (isSnapIt) {

            val lintMessage = when {
                !isComposable -> ComposableRequiredMsg(SNAP_IT)
                hasParams -> ZeroArgumentRequiredMsg(SNAP_IT)
                isPrivate -> PrivateModifierNotAllowed(SNAP_IT)
                else -> null
            }

            if (lintMessage != null) {
                reportError(
                    node = node,
                    lintMessage = lintMessage
                )
            }
        }
    }

    private fun reportError(
        node: UMethod,
        lintMessage: LintMessage
    ) {
        context.report(
            issue = ISSUE,
            scopeClass = node,
            location = context.getNameLocation(node),
            message = lintMessage.msg
        )
    }
}