package home.dj.excel.parser.generate.template

import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import freemarker.template.Version

fun getTemplate(templateName: String): Template? {
    val templateConfig = Configuration(Version(2,3,30))
    templateConfig.setClassLoaderForTemplateLoading(BusinessRuleTemplate::class.java.classLoader, "templates")
    templateConfig.defaultEncoding = "UTF-8"
    templateConfig.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
    return templateConfig.getTemplate(templateName)
}

class BusinessRuleTemplate