package home.dj.excel.parser.generate

import home.dj.excel.parser.generate.template.getTemplate
import home.dj.excel.parser.parse.model.EntityTemplateDTO
import home.dj.excel.parser.parse.model.RuleTemplateDTO
import java.io.File
import java.io.FileWriter

private const val RULE_TEMPLATE = "rule-template.ftl"
private const val ENTITY_TEMPLATE = "entity-template.ftl"
private val PATH_SEPARATOR = File.separator
private val PATH =
    "${System.getProperty("user.dir")}${PATH_SEPARATOR}build${PATH_SEPARATOR}generated${PATH_SEPARATOR}" +
            "src${PATH_SEPARATOR}main${PATH_SEPARATOR}kotlin${PATH_SEPARATOR}" +
            "home${PATH_SEPARATOR}dj${PATH_SEPARATOR}engine${PATH_SEPARATOR}rule"

class KotlinFileGenerator {

    private val ruleTemplate = getTemplate(RULE_TEMPLATE)
    private val entityTemplate = getTemplate(ENTITY_TEMPLATE)
    private val directory = File(PATH)

    init {
        if (!directory.exists()) directory.mkdirs()
    }

    fun generateBusinessRule(ruleTemplateDTO: RuleTemplateDTO) {
        val className = buildClassName(ruleTemplateDTO)
        val kotlinFile = File("$PATH/${className}.kt")
        val fileWriter = FileWriter(kotlinFile)
        val templateDataMap = mapOf(
            "ruleTemplateDTO" to ruleTemplateDTO,
            "className" to className
        )
        ruleTemplate!!.process(templateDataMap, fileWriter)
    }

    private fun buildClassName(ruleTemplateDTO: RuleTemplateDTO): String {
        return ruleTemplateDTO.ruleName
            .trim()
            .toLowerCase()
            .split(" ")
            .map { it.capitalize() }
            .reduce { s1, s2 -> s1 + s2 }
    }

    fun generateBusinessEntity(entityTemplateDTO: EntityTemplateDTO) {
        TODO("Not yet implemented")
    }
}