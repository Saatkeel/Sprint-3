package main.kotlin.ru.sber.nio

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.sber.nio.Grep
import java.io.File

internal class GrepTest {

    @Test
    fun find() {
        val grep = Grep()
        grep.find("23/Jan/2001:17:00:00 +0000")
        val file = File("result.txt")

        assertTrue(file.exists())

        file.delete()
    }
}