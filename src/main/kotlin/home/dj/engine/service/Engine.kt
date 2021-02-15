package home.dj.engine.service

import home.dj.engine.kafka.event.BaseEvent
import home.dj.engine.rule.BusinessRule
import javax.inject.Singleton

@Singleton
class Engine(private val rules: Collection<BusinessRule>) {

    fun applyRules(event: BaseEvent) = event.getAllEntities()
        .flatMap { it.getRuleEntityPairs() }
        .map { Pair(it.first, getRuleByName(it.second)) }
        .filter { it.second?.condition?.let { it1 -> it1(it.first) } ?: false }
        .forEach { it.second?.action?.let { it1 -> it1() } ?: Unit }

    private fun getRuleByName(ruleName: String): BusinessRule? {
        return rules.find { it.getName() == ruleName }
    }
}
