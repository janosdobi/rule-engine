package home.dj.engine.controller

import com.fasterxml.jackson.databind.ObjectMapper
import home.dj.engine.model.Response
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.jackson.ObjectMapperFactory
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.time.LocalDate
import javax.inject.Inject

@MicronautTest
internal class ControllerTest {

    @Inject
    @field:Client("/")
    lateinit var client: RxHttpClient

    @Inject
    lateinit var objectMapper: ObjectMapper

    @Test
    fun testHello() {
        val request: HttpRequest<Any> = HttpRequest.GET("/test")
        val body = client.toBlocking().retrieve(request)
        assertNotNull(body)
        assertEquals(objectMapper.writeValueAsString(Response(LocalDate.now(), 42)), body)
    }
}