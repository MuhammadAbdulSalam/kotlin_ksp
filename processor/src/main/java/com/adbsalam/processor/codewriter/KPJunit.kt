package com.adbsalam.processor.codewriter

import app.cash.paparazzi.Paparazzi
import com.adbsalam.annotations.SnapAnnotations
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import org.junit.Rule
import org.junit.runner.RunWith


internal fun jUnitClass(
    fileName: String,
    symbols: Sequence<KSFunctionDeclaration>,
    annotation: SnapAnnotations
): TypeSpec {

    val snapFunctions = snapFunctions(symbols)

    val runWith =
        AnnotationSpec.builder(RunWith::class).addMember("JUnit4::class").build()

    val ruleAnnotation = AnnotationSpec.builder(Rule::class).useSiteTarget(
        AnnotationSpec.UseSiteTarget.GET
    ).build()

    val codeBlock = CodeBlock.builder().add(paparazziInitializer(annotation)).build()

    val testRule = PropertySpec
        .builder("paparazzi", Paparazzi::class.java)
        .addAnnotation(ruleAnnotation)
        .initializer(codeBlock)
        .build()

    return TypeSpec.classBuilder(fileName)
        .addAnnotation(runWith)
        .addProperty(testRule)
        .addFunctions(snapFunctions)
        .build()
}

private fun paparazziInitializer(annotation: SnapAnnotations): String {
    return if (annotation == SnapAnnotations.SNAP_IT_COMPONENT)
        "Paparazzi.forComponent()"
    else
        "Paparazzi.forScreen()"
}
