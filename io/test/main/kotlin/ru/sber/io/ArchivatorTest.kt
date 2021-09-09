package main.kotlin.ru.sber.io

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.sber.io.Archivator
import java.io.File

internal class ArchivatorTest {
    private val archivator = Archivator()

    @Test
    fun zipLogfile() {
        archivator.zipLogfile(zippedFileName = "logfile.zip")
        val file = File("logfile.zip")

        assertTrue(file.exists())

        file.delete()
    }

    @Test
    fun unzipLogfile() {
        val originalFileName = "logfile.zip"
        archivator.zipLogfile(zippedFileName = originalFileName)
        archivator.unzipLogfile(unzippedFileName = "unzippedLogfile.log")
        val file = File("unzippedLogfile.log")

        assertTrue(file.exists())

        file.delete()
        File(originalFileName).delete()
    }
}