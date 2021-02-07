package home.dj.excel.parser

import home.dj.excel.parser.generate.BusinessRuleFileGenerator
import home.dj.excel.parser.parse.reader.ExcelReader
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class BusinessRuleParser : DefaultTask() {

    @TaskAction
    fun generateBusinessRuleFiles() {
        val businessRuleFileGenerator = BusinessRuleFileGenerator()
        ExcelReader().parseRuleDTOs().forEach { businessRuleFileGenerator.generateBusinessRule(it) }
    }
}