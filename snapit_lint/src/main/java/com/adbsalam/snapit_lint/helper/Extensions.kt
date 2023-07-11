package com.adbsalam.snapit_lint.helper

import com.intellij.lang.jvm.JvmModifier
import org.jetbrains.uast.UMethod

fun UMethod.hasSnapIt(
): Boolean {
    return this.annotations.firstOrNull {
        it.qualifiedName == SNAP_IT.byPackage()
    } != null
}

fun UMethod.isComposable(): Boolean {
    return this.annotations.firstOrNull {
        it.qualifiedName == COMPOSABLE
    } != null
}

fun UMethod.hasParams(): Boolean {
    return this.hasParameters()
}

fun UMethod.isPrivate(): Boolean {
    return this.hasModifier(JvmModifier.PRIVATE)
}

fun UMethod.errors(): LintMessage? {
    return when {
        !this.isComposable() -> ComposableRequiredMsg(SNAP_IT)
        this.hasParams() -> ZeroArgumentRequiredMsg(SNAP_IT)
        this.isPrivate() -> PrivateModifierNotAllowed(SNAP_IT)
        else -> null
    }
}