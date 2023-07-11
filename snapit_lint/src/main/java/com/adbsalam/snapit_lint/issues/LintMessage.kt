package com.adbsalam.snapit_lint.issues

sealed class LintMessage(val msg: String)

internal data class ComposableRequiredMsg(val annotation: String) : LintMessage(
    msg = """@$annotation requires @Composable annotation
        @$annotation can only be used with Composable function""".trimMargin()
)

internal data class ZeroArgumentRequiredMsg(val annotation: String) : LintMessage(
    msg = """function annotated with @$annotation cannot accept params
        @$annotation only accepts 0 param functions. Refactor function or remove annotation""".trimMargin()
)

internal data class PrivateModifierNotAllowed(val annotation: String) : LintMessage(
    msg = """function annotated with @$annotation cannot be private
        @$annotation does not accept private functions, Refactor function or remove annotation""".trimMargin()
)