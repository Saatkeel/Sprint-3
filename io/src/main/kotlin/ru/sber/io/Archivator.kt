package ru.sber.io

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile(fileName: String = "logfile.log", zippedFileName: String = "logfile.zip") {

        File(fileName)
                .inputStream()
                .use { input ->
            ZipOutputStream(File(zippedFileName).outputStream())
                    .use { output ->
                    val entry = ZipEntry(fileName)
                    output.putNextEntry(entry)
                    val buffer = ByteArray(input.available())
                    input.read(buffer)
                    output.write(buffer)
            }
        }
    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile(fileName: String = "logfile.zip", unzippedFileName: String = "unzippedLogfile.log") {
        ZipInputStream(File(fileName).inputStream())
                .use { input ->
            File(unzippedFileName)
                    .outputStream()
                    .use { output ->
                    while (input.nextEntry != null) {
                        var readInfo = input.read()
                        while (readInfo != -1) {
                            output.write(readInfo)
                            readInfo = input.read()
                        }
                    }
            }
        }
    }
}