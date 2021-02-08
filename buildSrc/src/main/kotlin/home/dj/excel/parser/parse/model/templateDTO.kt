package home.dj.excel.parser.parse.model

interface TemplateDTO {}

data class EntityTemplateDTO(
    val entityName: String,
    val propertyName: String,
    val propertyType: String
) : TemplateDTO

data class RuleTemplateDTO(
    val ruleName: String,
    val targetEntity: String,
    val targetProperty: String,
    val operatorType: String,
    val operatorName: String,
    val values: String
) : TemplateDTO