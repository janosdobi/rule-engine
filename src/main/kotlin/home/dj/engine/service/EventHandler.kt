package home.dj.engine.service

import home.dj.engine.kafka.Producer
import home.dj.engine.kafka.event.CompanyDataUpdatedEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import kotlin.random.Random

private val LOGGER: Logger = LoggerFactory.getLogger(EventHandler::class.java)
private const val RESULT_TOPIC = "test-result"

@Singleton
class EventHandler(private val engine: Engine, private val producer: Producer) {

    fun handle(event: CompanyDataUpdatedEvent) {
        LOGGER.info("CompanyDataUpdateEvent received {}", event)
        val result = engine.applyRules(event)
        producer.produce(RESULT_TOPIC, Random(System.nanoTime()).nextLong(), result)
        LOGGER.info("Result produced {}", result)
    }
}