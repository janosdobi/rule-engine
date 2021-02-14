package home.dj.engine.kafka

import home.dj.engine.kafka.event.CompanyDataUpdatedEvent
import home.dj.engine.service.EventHandler
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic

private const val TOPIC = "test-event"

@KafkaListener(offsetReset = OffsetReset.LATEST)
class Consumer(private val eventHandler: EventHandler) {

    @Topic(TOPIC)
    fun consume(@KafkaKey key: Long, event: CompanyDataUpdatedEvent) {
        eventHandler.handle(event)
    }
}