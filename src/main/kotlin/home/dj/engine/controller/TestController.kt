package home.dj.engine.controller

import home.dj.engine.kafka.Producer
import home.dj.engine.kafka.event.CompanyDataUpdatedEvent
import home.dj.engine.model.AddressDTO
import home.dj.engine.model.CompanyDTO
import home.dj.engine.model.FinancialDataDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import java.sql.Timestamp
import java.time.Instant
import kotlin.random.Random

private const val EVENT_TOPIC = "test-event"

@Controller("/test")
class TestController(private val producer: Producer) {

    @Get("/dummy-company-event")
    @Produces(MediaType.APPLICATION_JSON)
    fun index(): HttpResponse<Unit> {
        val id = Random(System.nanoTime()).nextLong()
        val address = AddressDTO(
            "Hungary", "Szeged", "Zarda", 16
        )
        val financialData = FinancialDataDTO(
            400_000.0, 400_000.0, 100_000.0, 50_000.0, 10_000.0
        )
        val companyData = CompanyDTO(
            "Test Company Ltd.",
            "1990-01-01",
            "Ltd", listOf("1111", "2222"),
            address,
            financialData
        )
        val event = CompanyDataUpdatedEvent(companyData, Timestamp.from(Instant.now()), id)
        producer.produce(EVENT_TOPIC, id, event)
        return HttpResponse.noContent()
    }
}