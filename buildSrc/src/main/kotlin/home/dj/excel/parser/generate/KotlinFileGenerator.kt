package home.dj.excel.parser.generate

import home.dj.excel.parser.generate.template.getTemplate
import home.dj.excel.parser.parse.model.EntityTemplate
import home.dj.excel.parser.parse.model.RuleTemplate
import java.io.File
import java.io.FileWriter

private const val RULE_TEMPLATE = "rule-template.ftl"
private const val ENTITY_TEMPLATE = "entity-template.ftl"
private val PATH_SEPARATOR = File.separator
private val RULE_PATH =
    "${System.getProperty("user.dir")}${PATH_SEPARATOR}build${PATH_SEPARATOR}generated${PATH_SEPARATOR}" +
            "src${PATH_SEPARATOR}main${PATH_SEPARATOR}kotlin${PATH_SEPARATOR}" +
            "home${PATH_SEPARATOR}dj${PATH_SEPARATOR}engine${PATH_SEPARATOR}rule"

private val ENTITY_PATH = "${System.getProperty("user.dir")}${PATH_SEPARATOR}build${PATH_SEPARATOR}generated${PATH_SEPARATOR}" +
        "src${PATH_SEPARATOR}main${PATH_SEPARATOR}kotlin${PATH_SEPARATOR}" +
        "home${PATH_SEPARATOR}dj${PATH_SEPARATOR}engine${PATH_SEPARATOR}model"

class KotlinFileGenerator {

    private val ruleTemplate = getTemplate(RULE_TEMPLATE)
    private val entityTemplate = getTemplate(ENTITY_TEMPLATE)
    private val ruleDirectory = File(RULE_PATH)
    private val entityDirectory = File(ENTITY_PATH)

    init {
        if (!ruleDirectory.exists()) ruleDirectory.mkdirs()
        if (!entityDirectory.exists()) entityDirectory.mkdirs()
    }

    fun generateBusinessRule(ruleTemplateDTO: RuleTemplate) {
        val className = buildClassName(ruleTemplateDTO.ruleName)
        val kotlinFile = File("$RULE_PATH/${className}.kt")
        val fileWriter = FileWriter(kotlinFile)
        val templateDataMap = mapOf(
            "ruleTemplateDTO" to ruleTemplateDTO,
            "className" to className
        )
        ruleTemplate!!.process(templateDataMap, fileWriter)
    }

    fun generateBusinessEntity(entityTemplateDTO: EntityTemplate) {
        val className = buildClassName(entityTemplateDTO.entityName)
        val kotlinFile = File("$ENTITY_PATH/${className}DTO.kt")
        val fileWriter = FileWriter(kotlinFile)
        val templateDataMap = mapOf(
            "entityTemplateDTO" to entityTemplateDTO,
            "className" to className
        )
        entityTemplate!!.process(templateDataMap, fileWriter)
    }

    private fun buildClassName(rawClassName: String): String {
        return rawClassName
            .trim()
            .split(" ")
            .map { it.capitalize() }
            .reduce { s1, s2 -> s1 + s2 }
    }
}