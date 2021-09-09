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
                .use { fis ->
            ZipOutputStream(File(zippedFileName).outputStream())
                    .use { fos ->
                    val entry = ZipEntry(fileName)
                    fos.putNextEntry(entry)
                    val buffer = ByteArray(fis.available())
                    fis.read(buffer)
                    fos.write(buffer)
            }
        }
    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile(fileName: String = "logfile.zip", unzippedFileName: String = "unzippedLogfile.log") {
        ZipInputStream(File(fileName).inputStream())
                .use { fis ->
            File(unzippedFileName)
                    .outputStream()
                    .use { fos ->
                    while (fis.nextEntry != null) {
                        var readInfo = fis.read()
                        while (readInfo != -1) {
                            fos.write(readInfo)
                            readInfo = fis.read()
                        }
                    }
            }
        }
    }
}