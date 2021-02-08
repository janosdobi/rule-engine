package home.dj.excel.parser

import home.dj.excel.parser.generate.BusinessRuleFileGenerator
import home.dj.excel.parser.parse.model.EntityTemplateDTO
import home.dj.excel.parser.parse.model.RuleTemplateDTO
import home.dj.excel.parser.parse.reader.ENTITY_SHEET
import home.dj.excel.parser.parse.reader.ExcelReader
import home.dj.excel.parser.parse.reader.RULE_SHEET
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class BusinessRuleParser : DefaultTask() {

    @TaskAction
    fun generateBusinessRuleFiles() {
        val businessRuleFileGenerator = BusinessRuleFileGenerator()
        ExcelReader().parseDTOs(ENTITY_SHEET).forEach { businessRuleFileGenerator.generateBusinessEntity(it as EntityTemplateDTO) }
        ExcelReader().parseDTOs(RULE_SHEET).forEach { businessRuleFileGenerator.generateBusinessRule(it as RuleTemplateDTO) }
    }
}