package home.dj.excel.parser.parse.model

interface Template

data class EntityTemplate(
    val entityName: String,
    val properties: Collection<Pair<String, String>>
) : Template

data class RuleTemplate(
    val ruleName: String,
    val targetEntity: String,
    val targetProperty: String,
    val operatorType: String,
    val operatorName: String,
    val values: String
) : Template