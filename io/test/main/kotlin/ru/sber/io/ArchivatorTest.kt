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
        assertTrue(File("logfile.zip").exists())
    }

    @Test
    fun unzipLogfile() {
        archivator.unzipLogfile(unzippedFileName = "unzippedLogfile.log")
        assertTrue(File("unzippedLogfile.log").exists())
    }
}