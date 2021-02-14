package home.dj.excel.parser.generate

import home.dj.excel.parser.parse.model.EntityExcelDTO
import home.dj.excel.parser.parse.model.RuleExcelDTO

private const val ENTITY_TEMPLATE = "entity-template.ftl"
private val ENTITY_PATH =
    "${System.getProperty("user.dir")}${PATH_SEPARATOR}build${PATH_SEPARATOR}generated${PATH_SEPARATOR}" +
            "src${PATH_SEPARATOR}main${PATH_SEPARATOR}kotlin${PATH_SEPARATOR}" +
            "home${PATH_SEPARATOR}dj${PATH_SEPARATOR}engine${PATH_SEPARATOR}model"

class EntityGenerator : KotlinFileGenerator() {

    override fun getTemplateName(): String {
        return ENTITY_TEMPLATE
    }

    override fun getDestinationPath(): String {
        return ENTITY_PATH
    }

    fun generateKotlinFile(entityDTO: EntityExcelDTO, ruleDTOs: Collection<RuleExcelDTO>) {
        val rules = ruleDTOs.filter { it.targetEntity == entityDTO.entityName }
        super.generateKotlinFile(entityDTO, rules)
    }
}