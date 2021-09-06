package ru.sber.qa

import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.sber.qa.Certificate
import ru.sber.qa.CertificateRequest
import kotlin.random.Random

internal class CertificateTest {

    private val certificateReq = mockk<CertificateRequest>(relaxed = true)
    private val processedBy = 100L
    private val data = Random.nextBytes(100)
    private val certificate = Certificate(certificateReq, processedBy, data)

    @Test
    fun getCertificateRequest() {
        assertEquals(certificateReq, certificate.certificateRequest)
    }

    @Test
    fun getProcessedBy() {
        assertEquals(processedBy, certificate.processedBy)
    }

    @Test
    fun getData() {
        assertEquals(data, certificate.data)
    }
}