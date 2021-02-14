package home.dj.engine.model

import home.dj.engine.rule.BusinessRule

interface DataEntity {
    val getRules: () -> Collection<BusinessRule>
}