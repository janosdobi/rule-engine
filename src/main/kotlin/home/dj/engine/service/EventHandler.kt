package home.dj.engine.service

import home.dj.engine.kafka.event.CompanyDataUpdatedEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

private val LOGGER: Logger = LoggerFactory.getLogger(EventHandler::class.java)

@Singleton
class EventHandler(private val engine: Engine) {

    fun handle(event: CompanyDataUpdatedEvent) {
        LOGGER.info("CompanyDataUpdatedEvent received {}", event)
        engine.applyRules(event)
    }
}
