package home.dj.excel.parser.parse.model

data class RuleTemplateDTO(
    val className: String,
    val targetEntity: String,
    val targetProperty: String,
    val operator: String,
    val value: Double
)