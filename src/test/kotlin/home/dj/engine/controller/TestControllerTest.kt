package home.dj.engine.controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class TestControllerTest {

    @Inject
    @field:Client("/")
    lateinit var client: RxHttpClient

    @Test
    @Disabled
    fun testHello() {
        val request: HttpRequest<Any> = HttpRequest.GET("/test/dummy-company-event")
        client.toBlocking().retrieve(request)
    }
}