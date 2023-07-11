package com.adbsalam.snapit_lint.issues

internal sealed class LintMessage(val msg: String)

internal object DuplicateAnnotationMsg : LintMessage(
    msg = """@SnapIt and @ScreenSnapIt cannot be used for same method
        Please use either @SnapIt or @ScreenSnapIt for this method
        Alternatively two methods can be used, one with @SnapIt and second with @ScreenSnapIt"""
)

internal data class ComposableRequiredMsg(val annotation: String) : LintMessage(
    msg = """@$annotation requires @Composable annotation
        @$annotation can only be used with Composable method""".trimMargin()
)

internal data class ZeroArgumentRequiredMsg(val annotation: String) : LintMessage(
    msg = """Method annotated with @$annotation cannot accept params
        @$annotation only accepts 0 param methods. Refactor method or remove annotation"""
)

internal data class PrivateModifierNotAllowed(val annotation: String) : LintMessage(
    msg = """Method annotated with @$annotation cannot be private
        @$annotation does not accept private functions, Refactor method or remove annotation"""
)