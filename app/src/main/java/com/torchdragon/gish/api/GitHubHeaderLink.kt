package com.torchdragon.gish.api

import retrofit2.Response

class GitHubHeaderLink(raw: String?) {

    val next: String?

    init {
        next = when (raw) {
            null -> null
            else -> {
                val parts = Regex(PARSE_PATTERN).findAll(raw)

                parts.find { match -> match.groupValues[2] == "next" }?.let {
                    it.groupValues[1]
                }
            }
        }
    }

    companion object {
        internal const val PARSE_PATTERN = """(?><(.+?)>; rel="(\w+?)")"""
    }
}

fun Response<*>.parseLink(): GitHubHeaderLink {
    return GitHubHeaderLink(headers().get("link"))
}
