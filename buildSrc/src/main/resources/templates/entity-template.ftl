package home.dj.engine.model

import home.dj.engine.rule.BusinessRule
import java.util.*

data class ${className}(
<#list templateDTO.properties as property>
    val ${property.first}: ${property.second},
</#list>
) : DataEntity {
    override val getRules = { Collections.emptyList<BusinessRule>() }
}