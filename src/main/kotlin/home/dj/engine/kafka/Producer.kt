package home.dj.engine.kafka

import home.dj.engine.kafka.event.CompanyDataUpdatedEvent
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface Producer {

    @Topic("test-event")
    fun produce(@KafkaKey key: Long, event: CompanyDataUpdatedEvent)
}