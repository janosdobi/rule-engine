package home.dj.excel.parser.parse.reader

import home.dj.excel.parser.parse.model.EntityTemplateDTO
import home.dj.excel.parser.parse.model.RuleTemplateDTO
import home.dj.excel.parser.parse.model.TemplateDTO
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFWorkbook

const val RULE_SHEET = "Rules"
const val ENTITY_SHEET = "Entities"
private const val EXCEL_FILE_NAME = "business_rules.xlsx"

class ExcelReader {

    private val businessRuleExcel = XSSFWorkbook(EXCEL_FILE_NAME)

    fun parseDTOs(sheetName: String): Collection<TemplateDTO> {
        return businessRuleExcel.getSheet(sheetName)
            .filter { it.getCell(1) != null && it.getCell(1).cellType != CellType.BLANK && it.rowNum != 1 }
            .map { if (RULE_SHEET == sheetName) mapExcelRowToRule(it) else mapExcelRowToEntity(it) }
    }

    private fun mapExcelRowToEntity(row: Row?): EntityTemplateDTO {
        return EntityTemplateDTO(
            entityName = row!!.getCell(1).stringCellValue,
            propertyName = row.getCell(2).stringCellValue,
            propertyType = row.getCell(3).stringCellValue
        )
    }

    private fun mapExcelRowToRule(row: Row?): RuleTemplateDTO {
        return RuleTemplateDTO(
            ruleName = row!!.getCell(1).stringCellValue,
            targetEntity = row.getCell(2).stringCellValue,
            targetProperty = row.getCell(3).stringCellValue,
            operatorType = row.getCell(4).stringCellValue,
            operatorName = row.getCell(5).stringCellValue,
            values = if (row.getCell(6).cellType == CellType.STRING) row.getCell(6).stringCellValue else row.getCell(6).numericCellValue.toString()
        )
    }
}