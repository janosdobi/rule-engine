package home.dj.engine.service

import home.dj.engine.kafka.event.BaseEvent
import javax.inject.Singleton

@Singleton
class Engine {
    fun applyRules(event: BaseEvent) = event.entity.getRules()
        .filter { it.condition(event.entity) }
        .forEach { it.action }
}