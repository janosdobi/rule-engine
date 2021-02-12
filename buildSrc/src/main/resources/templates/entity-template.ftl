package home.dj.engine.model

data class ${className}DTO(
    <#list entityTemplateDTO.properties as property>
        val ${property.first}: ${property.second},
    </#list>
) : DataEntity