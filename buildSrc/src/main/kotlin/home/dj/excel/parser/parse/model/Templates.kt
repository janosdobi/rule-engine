package home.dj.excel.parser.parse.model

sealed class ExcelDTO{
    abstract val rawClassName: String
}

data class EntityExcelDTO(
    val entityName: String,
    val properties: Collection<Pair<String, String>>,
    override val rawClassName: String = entityName
) : ExcelDTO()

data class RuleExcelDTO(
    val ruleName: String,
    val targetEntity: String,
    val targetProperty: String,
    val operatorType: String,
    val operatorName: String,
    val values: String,
    override val rawClassName: String = ruleName
) : ExcelDTO()