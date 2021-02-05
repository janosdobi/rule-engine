package home.dj.engine.controller

import home.dj.engine.model.Response
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import java.time.LocalDate

@Controller("/test")
class Controller {

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun index(): Response {
        return Response(LocalDate.now(), 42)
    }
}