package home.dj.engine.service

import home.dj.engine.kafka.event.BaseEvent
import home.dj.engine.rule.BusinessRule
import javax.inject.Singleton
import home.dj.engine.util.inlineAllMemberDataEntities

@Singleton
class Engine(private val rules: Collection<BusinessRule>) {

    fun applyRules(event: BaseEvent) = inlineAllMemberDataEntities(event.entity)
        .flatMap { it.getEntityRuleNamePairs() }
        .map { Pair(it.first, getRuleByName(it.second)) }
        .filter { it.second?.condition?.let { it1 -> it1(it.first) } ?: false }
        .forEach { it.second?.action?.let { it1 -> it1() } ?: Unit }

    private fun getRuleByName(ruleName: String): BusinessRule? {
        return rules.find { it.getName() == ruleName }
    }
}
