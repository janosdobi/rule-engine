package home.dj.engine.kafka

import home.dj.engine.kafka.event.CompanyDataUpdatedEvent
import home.dj.engine.service.EventHandler
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
class Consumer(private val eventHandler: EventHandler) {

    @Topic("test-event")
    fun consume(@KafkaKey key: Long, event: CompanyDataUpdatedEvent) {
        eventHandler.handle(event)
    }
}