package home.dj.engine.model

import com.fasterxml.jackson.annotation.JsonIgnore
import home.dj.engine.rule.*
import java.util.*

data class ${className}(
<#list data.properties as property>
    val ${property.first}: ${property.second},
</#list>
) : DataEntity {
    @Transient
    @JsonIgnore
    override val getEntityRuleNamePairs = {
        <#if ruleNames?size != 0>
        listOf(
        <#list ruleNames as ruleName>
            Pair(this, "${ruleName}"),
        </#list>
        )
        <#else>
        Collections.emptyList<Pair<DataEntity, String>>()
        </#if>
    }
}