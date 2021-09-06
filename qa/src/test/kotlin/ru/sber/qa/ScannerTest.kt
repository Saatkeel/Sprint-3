package ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.sber.qa.ScanTimeoutException
import ru.sber.qa.Scanner
import kotlin.random.Random

internal class ScannerTest {

    @BeforeEach
    fun setUp() {
        mockkObject(Random)
    }

    @Test
    fun testScanTimeoutException(){
        every{Random.nextLong(5000L, 15000L)} returns 10_001L

        assertThrows(ScanTimeoutException::class.java)
        {
            Scanner.getScanData()
        }
    }
    @Test
    fun testGetScanData(){
        every{Random.nextLong(5000L, 15000L)} returns 6000L
        val array = Random.nextBytes(100)
        every{Random.nextBytes(100)} returns array

        assertEquals(array,Scanner.getScanData())
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
        // Моки вынесены в методы after/before тк для каждого из 2 тк надо мокировать Рандом(как я это понимаю)
}