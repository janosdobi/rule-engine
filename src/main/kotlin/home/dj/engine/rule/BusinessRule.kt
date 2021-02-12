package home.dj.engine.rule

import home.dj.engine.model.DataEntity

interface BusinessRule {
    val condition: (DataEntity) -> Boolean
    val action: () -> Unit
}
