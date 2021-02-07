package home.dj.engine.rule

import home.dj.engine.kafka.event.BaseEvent
import home.dj.engine.model.Consequence

interface BusinessRule {

    fun apply(event: BaseEvent): Consequence
}