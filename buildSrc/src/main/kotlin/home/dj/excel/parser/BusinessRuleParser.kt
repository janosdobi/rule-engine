package home.dj.excel.parser

import home.dj.excel.parser.generate.BusinessRuleGenerator
import home.dj.excel.parser.generate.EntityGenerator
import home.dj.excel.parser.parse.reader.ExcelReader
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class BusinessRuleParser : DefaultTask() {

    @TaskAction
    fun generateKotlinFiles() {

        val businessRuleGenerator = BusinessRuleGenerator()
        val entityGenerator = EntityGenerator()

        val parsedEntities = ExcelReader().parseEntities()
        val parsedBusinessRules = ExcelReader().parseBusinessRules()

        parsedBusinessRules.forEach { businessRuleGenerator.generateKotlinFile(it) }
        parsedEntities.forEach { entityGenerator.generateKotlinFile(it, parsedBusinessRules) }

    }
}
