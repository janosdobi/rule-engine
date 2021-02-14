package home.dj.excel.parser.generate

private const val RULE_TEMPLATE = "rule-template.ftl"
private val RULE_PATH =
    "${System.getProperty("user.dir")}${PATH_SEPARATOR}build${PATH_SEPARATOR}generated${PATH_SEPARATOR}" +
            "src${PATH_SEPARATOR}main${PATH_SEPARATOR}kotlin${PATH_SEPARATOR}" +
            "home${PATH_SEPARATOR}dj${PATH_SEPARATOR}engine${PATH_SEPARATOR}rule"

class BusinessRuleGenerator : KotlinFileGenerator() {

    override fun getTemplateName(): String {
        return RULE_TEMPLATE
    }

    override fun getDestinationPath(): String {
        return RULE_PATH
    }
}