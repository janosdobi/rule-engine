package home.dj.excel.parser.parse.reader

import home.dj.excel.parser.parse.model.RuleTemplateDTO
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.util.*

class ExcelReader {

    private val businessRuleExcel = XSSFWorkbook("business_rules.xlsx")

    fun parseRuleDTOs(): Collection<RuleTemplateDTO> {
        val sheet = businessRuleExcel.getSheet("Rules")
        val row = sheet.getRow(1)
        val cell = row.getCell(1)
        return Collections.emptyList()
    }
}