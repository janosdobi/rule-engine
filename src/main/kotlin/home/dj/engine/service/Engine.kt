package home.dj.engine.service

import home.dj.engine.kafka.event.BaseEvent
import home.dj.engine.model.Consequence
import home.dj.engine.rule.BusinessRule
import javax.inject.Singleton

@Singleton
class Engine {
    fun applyRules(event: BaseEvent): BaseEvent {
        val consequences = getRulesForEvent(event).map { it.apply(event) }
        return getResultEventFromConsequences(consequences)
    }

    private fun getRulesForEvent(event: BaseEvent): Collection<BusinessRule> {
        TODO("Not yet implemented")
    }

    private fun getResultEventFromConsequences(consequences: Collection<Consequence>): BaseEvent {
        TODO("Not yet implemented")
    }
}