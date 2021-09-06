package ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class CertificateRequestTest {

    private val employeeNumber = 100L
    private val certificateType = mockk<CertificateType>(relaxed = true)
    private val certificateRequest = CertificateRequest(employeeNumber, certificateType)
    private val hrEmployeeNumber = 50L
    private val data = Random.nextBytes(100)

    @Test
    fun process() {
        mockkObject(Scanner)
        every { Scanner.getScanData() } returns data

        val certificate = certificateRequest.process(hrEmployeeNumber)

        assertEquals(data, certificate.data)
        assertEquals(hrEmployeeNumber, certificate.processedBy)
        assertEquals(certificateRequest, certificate.certificateRequest)

        unmockkAll()        // тут моки не вынесены потому что для других двух тестов нет смысла мокировать объект
    }

    @Test
    fun getEmployeeNumber() {
        assertEquals(employeeNumber, certificateRequest.employeeNumber)
    }

    @Test
    fun getCertificateType() {
        assertEquals(certificateType, certificateRequest.certificateType)
    }

}

