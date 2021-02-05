package home.dj.excel.parser.generate

import home.dj.excel.parser.generate.template.getTemplate
import home.dj.excel.parser.parse.model.RuleTemplateDTO
import java.io.File
import java.io.FileWriter

private const val PATH = "/Users/jdobi/Documents/rule-engine/build/generated/src/main/kotlin/rule/engine/rules"
private const val RULE_TEMPLATE = "rule-template.ftl"
private const val INTERFACE_TEMPLATE = "business-rule-interface-template.ftl"

class BusinessRuleFileGenerator {

    private val ruleTemplate = getTemplate(RULE_TEMPLATE)
    private val interfaceTemplate = getTemplate(INTERFACE_TEMPLATE)
    private val directory = File(PATH)

    init {
        if (!directory.exists()) directory.mkdirs()
    }

    fun generateBusinessRule(ruleTemplateDTO: RuleTemplateDTO) {
        val kotlinFile = File("$PATH/${ruleTemplateDTO.className}.kt")
        val fileWriter = FileWriter(kotlinFile)
        ruleTemplate!!.process(mapOf("ruleTemplateDTO" to ruleTemplateDTO), fileWriter)
    }

    fun generateInterface() {
        val interfaceFile = File("$PATH/BusinessRule.kt")
        val fileWriter = FileWriter(interfaceFile)
        interfaceTemplate!!.process(Object(), fileWriter)
    }
}