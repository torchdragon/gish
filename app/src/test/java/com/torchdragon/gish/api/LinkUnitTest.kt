package com.torchdragon.gish.api

import org.junit.Assert.assertEquals
import org.junit.Test

class LinkUnitTest {

    private val linkContent = """
        <https://api.github.com/resource?page=2>; rel="next", <https://api.github.com/resource?page=5>; rel="last"
        """.trimIndent()

    @Test
    fun parsesNextCorrectly() {
        val link = linkContent.parseLink()

        assertEquals("https://api.github.com/resource?page=2", link.next)
    }
}
