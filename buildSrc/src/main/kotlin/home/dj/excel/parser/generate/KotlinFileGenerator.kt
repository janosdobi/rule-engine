package home.dj.excel.parser.generate

import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import freemarker.template.Version
import home.dj.excel.parser.parse.model.ExcelDTO
import java.io.File
import java.io.FileWriter

internal val PATH_SEPARATOR = File.separator

abstract class KotlinFileGenerator {

    private val freemarkerTemplate = getFreemarkerTemplate(getTemplateName())
    private val destinationDirectory = File(getDestinationPath())

    init {
        if (!destinationDirectory.exists()) destinationDirectory.mkdirs()
    }

    abstract fun getTemplateName(): String
    abstract fun getDestinationPath(): String

    fun generateKotlinFile(excelDTO: ExcelDTO) {
        this.generateKotlinFile(excelDTO, null)
    }

    fun generateKotlinFile(excelDTO: ExcelDTO, rules: Collection<ExcelDTO>?) {
        val className = buildClassName(excelDTO.rawClassName)
        val kotlinFile = File("${getDestinationPath()}/${className}.kt")
        val fileWriter = FileWriter(kotlinFile)
        val templateDataMap = mutableMapOf(
            "data" to excelDTO,
            "className" to className,

        )

        rules?.let {
            templateDataMap["ruleNames"] = rules.map { buildClassName(it.rawClassName) }
        }

        freemarkerTemplate!!.process(templateDataMap, fileWriter)
    }

    private fun buildClassName(rawClassName: String): String {
        return rawClassName
            .trim()
            .split(" ")
            .map { it.capitalize() }
            .reduce { s1, s2 -> s1 + s2 }
    }

    private fun getFreemarkerTemplate(templateName: String): Template? {
        val templateConfig = Configuration(Version(2, 3, 30))
        templateConfig.setClassLoaderForTemplateLoading(KotlinFileGenerator::class.java.classLoader, "templates")
        templateConfig.defaultEncoding = "UTF-8"
        templateConfig.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
        return templateConfig.getTemplate(templateName)
    }
}
