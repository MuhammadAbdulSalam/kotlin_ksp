package com.adbsalam.processor.codewriter

import app.cash.paparazzi.Paparazzi
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.Modifier
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import org.junit.Rule
import org.junit.runner.RunWith
import java.lang.reflect.Type
import java.util.Objects
import javax.lang.model.type.TypeMirror
import kotlin.reflect.KClass


internal fun jUnitClass(
    fileName: String,
    symbols: Sequence<KSFunctionDeclaration>
): TypeSpec {

    val snapFunctions = snapFunctions(symbols)

    val runWith =
        AnnotationSpec.builder(RunWith::class).addMember("JUnit4::class").build()

    val ruleAnnotation = AnnotationSpec.builder(Rule::class).build()
    val jvmAnnotation = AnnotationSpec.builder(JvmField::class).build()

    val codeBlock = CodeBlock.builder().add(
        "Paparazzi.forComponent()"
    ).build()


    val testRule = PropertySpec
        .builder("paparazzi", Paparazzi::class.java)
        .addAnnotation(jvmAnnotation)
        .addAnnotation(ruleAnnotation)
        .initializer(codeBlock)
        .build()

    return TypeSpec.classBuilder(fileName)
        .addAnnotation(runWith)
        .addProperty(testRule)
        .addFunctions(snapFunctions)
        .build()
}
