package ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

internal class HrDepartmentTest {

    private val certificate = mockk<Certificate>()
    private val certificateRequest = mockk<CertificateRequest>()
    private val hrEmployeeNumber = 50L

    @Test
    fun receiveRequestDoesNotThrow() {
        HrDepartment.clock = Clock.fixed(
                Instant.parse("2021-09-03T10:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.NDFL

        assertDoesNotThrow{HrDepartment.receiveRequest(certificateRequest)}
    }

    @Test
    fun processNextRequestDoesNotThrow() {
        HrDepartment.clock = Clock.fixed(
                Instant.parse("2021-09-01T10:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.NDFL
        every { certificateRequest.process(hrEmployeeNumber) } returns certificate

        HrDepartment.receiveRequest(certificateRequest)

        assertDoesNotThrow{
            HrDepartment.processNextRequest(hrEmployeeNumber)}
    }

    @Test
    fun receiveRequestThrowOnWeekend() {
        //Sunday
        HrDepartment.clock = Clock.fixed(
                Instant.parse("2021-09-05T10:00:00Z"), ZoneOffset.UTC)

        every { certificateRequest.certificateType } returns CertificateType.NDFL

        assertThrows(
                WeekendDayException::class.java,
                {HrDepartment.receiveRequest(certificateRequest)},
                "Must throw on any day off"
        )
    }

    @Test
    fun recieveRequestThrowsOnEvenDays() {
        //Thursday
        HrDepartment.clock = Clock.fixed(
                Instant.parse("2021-09-02T12:00:00Z"), ZoneOffset.UTC)

        every { certificateRequest.certificateType } returns CertificateType.NDFL

        assertThrows(
                NotAllowReceiveRequestException::class.java,
                {HrDepartment.receiveRequest(certificateRequest)},
                "Must throw on any even days"
        )
    }

    @Test
    fun recieveRequestThrowsOnOddDays() {
        //Friday
        HrDepartment.clock = Clock.fixed(
                Instant.parse("2021-09-03T12:00:00Z"), ZoneOffset.UTC)

        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK

        assertThrows(NotAllowReceiveRequestException::class.java) {
            HrDepartment.receiveRequest(certificateRequest)
        }
    }
}