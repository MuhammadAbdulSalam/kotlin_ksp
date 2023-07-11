package com.adbsalam.snapit_lint

import com.adbsalam.snapit_lint.issues.SnapItIssues
import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API

class SnapItLint : IssueRegistry() {
    override val issues = listOf(SnapItIssues.ISSUE)

    override val api: Int = CURRENT_API

    override val minApi: Int = 6
}