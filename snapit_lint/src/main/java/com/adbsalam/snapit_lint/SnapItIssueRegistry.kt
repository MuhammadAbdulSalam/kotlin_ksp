package com.adbsalam.snapit_lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API

class SnapItIssueRegistry : IssueRegistry() {
    override val issues = listOf(SnapItIssues.ISSUE)

    override val api: Int = CURRENT_API

    override val minApi: Int = 6
}