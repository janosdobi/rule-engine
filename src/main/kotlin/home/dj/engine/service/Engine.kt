package home.dj.engine.service

import home.dj.engine.kafka.event.BaseEvent
import javax.inject.Singleton

@Singleton
class Engine {
    //TODO business rules should be instantiated only once
    fun applyRules(event: BaseEvent) = event.getAllEntities()
        .flatMap { it.getRuleEntityPairs() }
        .filter { it.second.condition(it.first) }
        .forEach { it.second.action() }
}
