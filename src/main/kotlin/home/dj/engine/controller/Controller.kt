package home.dj.engine.controller

import home.dj.engine.model.Response
import home.dj.engine.service.RuleApplicator
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import java.time.LocalDate

@Controller("/test")
class Controller(private val ruleApplicator: RuleApplicator) {

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun index(): Response {
        ruleApplicator.applyRules()
        return Response(LocalDate.now(), 42)
    }
}