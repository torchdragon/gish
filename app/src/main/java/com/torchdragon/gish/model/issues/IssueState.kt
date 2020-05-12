package com.torchdragon.gish.model.issues

import androidx.annotation.ColorRes
import com.torchdragon.gish.R

enum class IssueState(val type: String, @ColorRes val color: Int) {
    closed("closed", R.color.issueClosed),
    `open`("open", R.color.issueOpen),
    all("all", android.R.color.transparent)
}
