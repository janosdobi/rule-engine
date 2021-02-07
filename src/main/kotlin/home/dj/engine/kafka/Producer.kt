package home.dj.engine.kafka

import home.dj.engine.kafka.event.BaseEvent
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface Producer {
    fun produce(@Topic topic: String, @KafkaKey key: Long, event: BaseEvent)
}