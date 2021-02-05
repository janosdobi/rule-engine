package home.dj.excel.parser

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import home.dj.excel.parser.generate.BusinessRuleFileGenerator
import home.dj.excel.parser.parse.model.RuleTemplateDTO
import home.dj.excel.parser.parse.reader.ExcelReader

open class BusinessRuleParser : DefaultTask() {

    @TaskAction
    fun generateBusinessRuleFiles() {
        val businessRuleFileGenerator = BusinessRuleFileGenerator()
        //ExcelReader().parseRuleDTOs().forEach { businessRuleFileGenerator.generateBusinessRule(it) }
        businessRuleFileGenerator.generateInterface()
        businessRuleFileGenerator.generateBusinessRule(RuleTemplateDTO("TestClass", "Company", "age", ">", 9.0))
    }
}