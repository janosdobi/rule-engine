package home.dj.excel.parser.parse.reader

import home.dj.excel.parser.parse.model.EntityExcelDTO
import home.dj.excel.parser.parse.model.RuleExcelDTO
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.time.ZoneId

const val RULE_SHEET = "Rules"
const val ENTITY_SHEET = "Entities"
private const val EXCEL_FILE_NAME = "business_rules.xlsx"

class ExcelReader {

    private val businessRuleExcel = XSSFWorkbook(EXCEL_FILE_NAME)

    fun parseBusinessRules(): Collection<RuleExcelDTO> {
        return businessRuleExcel.getSheet(RULE_SHEET)
            .filter { it.getCell(1) != null && it.getCell(1).cellType != CellType.BLANK && it.rowNum != 1 }
            .map { mapExcelRowToRule(it) }
    }

    fun parseEntities(): List<EntityExcelDTO> {
        return businessRuleExcel.getSheet(ENTITY_SHEET)
            .filter { it.getCell(1) != null && it.getCell(1).cellType != CellType.BLANK && it.rowNum != 1 }
            .map { EntitySheetItem.fromExcelRow(it) }
            .groupBy { it.entityName }
            .map { mapToEntityTemplateDTO(it.value) }
    }

    private fun mapToEntityTemplateDTO(groupedProperties: List<EntitySheetItem>): EntityExcelDTO {
        return EntityExcelDTO(
            entityName = groupedProperties.first().entityName,
            properties = groupedProperties.map { it.property }
        )
    }

    private fun mapExcelRowToRule(row: Row?): RuleExcelDTO {
        return RuleExcelDTO(
            ruleName = row!!.getCell(1).stringCellValue,
            targetEntity = row.getCell(2).stringCellValue,
            targetProperty = row.getCell(3).stringCellValue,
            operatorType = row.getCell(4).stringCellValue,
            operatorName = row.getCell(5).stringCellValue,
            values = if (row.getCell(6).cellType == CellType.STRING) row.getCell(6).stringCellValue
            else if (DateUtil.isCellDateFormatted(row.getCell(6))) row.getCell(6).dateCellValue.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate().toString()
            else row.getCell(6).numericCellValue.toString()
        )
    }

    private data class EntitySheetItem(
        val entityName: String,
        val property: Pair<String, String>
    ) {
        companion object Builder {
            fun fromExcelRow(row: Row): EntitySheetItem {
                return EntitySheetItem(
                    entityName = row.getCell(1).stringCellValue,
                    property = Pair(row.getCell(2).stringCellValue, row.getCell(3).stringCellValue)
                )
            }
        }
    }
}