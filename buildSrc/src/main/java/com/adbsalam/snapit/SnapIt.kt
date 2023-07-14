package com.adbsalam.snapit

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.the

class SnapIt : Plugin<Project> {

    override fun apply(project: Project) {

        project.extensions.create<SnapPath>(PATH_EXTENSION).testDir.set("")

        project.snapItDependencies()

        project.snapItPlugins()

        project.snapItGenerateTask()
    }
}
