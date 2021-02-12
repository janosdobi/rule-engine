package home.dj.excel.parser

import home.dj.excel.parser.generate.KotlinFileGenerator
import home.dj.excel.parser.parse.reader.ExcelReader
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class BusinessRuleParser : DefaultTask() {

    @TaskAction
    fun generateBusinessRuleFiles() {
        val kotlinFileGenerator = KotlinFileGenerator()
        ExcelReader().parseEntities().forEach { kotlinFileGenerator.generateBusinessEntity(it) }
        ExcelReader().parseBusinessRules().forEach { kotlinFileGenerator.generateBusinessRule(it) }
    }
}