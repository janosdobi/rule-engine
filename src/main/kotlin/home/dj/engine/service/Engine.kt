package home.dj.engine.service

import home.dj.engine.kafka.event.BaseEvent
import home.dj.engine.model.Action
import home.dj.engine.model.DataEntity
import home.dj.engine.rule.BusinessRule
import javax.inject.Singleton

@Singleton
class Engine {
    fun applyRules(event: BaseEvent) = getRulesForEntity(event.entity)
        .filter { it.condition(event.entity) }
        .forEach { it.action }

    private fun getRulesForEntity(entity: DataEntity): Collection<BusinessRule> {
        TODO("Not yet implemented")
    }
}