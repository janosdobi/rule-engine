package home.dj.engine.rule

import home.dj.engine.model.*
import javax.inject.Singleton
<#if data.operatorType == "date">
import java.time.LocalDate
import java.time.format.DateTimeFormatter
</#if>

<#if data.operatorType == "collection/string" && data.operatorName == "in">
private val COLLECTION_TO_CHECK = "${data.values}".split(";")
<#elseif data.operatorType == "date">
private val DATE_TO_CHECK = LocalDate.parse("${data.values}", DateTimeFormatter.ofPattern("yyyy-MM-dd"))
<#elseif data.operatorType == "integer">
private val VALUE_TO_CHECK = ${data.values}
</#if>

@Singleton
class ${className} : BusinessRule {

    override val condition = { dataEntity : DataEntity ->
        if (dataEntity is ${data.targetEntity})
            <#if data.operatorType == "collection/string" && data.operatorName == "in">
            COLLECTION_TO_CHECK.contains(dataEntity.${data.targetProperty})
            <#elseif data.operatorType == "date" && data.operatorName == "after">
            DATE_TO_CHECK.isAfter(LocalDate.parse(dataEntity.${data.targetProperty}, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
            <#elseif data.operatorType == "integer" && data.operatorName == "gt">
            VALUE_TO_CHECK < dataEntity.${data.targetProperty}
            </#if>
        else false
    }

    override val action = { println("Executed some action based on $this.javaClass.simpleName") }

    override fun getName(): String? {
        return this::class.simpleName
    }
}