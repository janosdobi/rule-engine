package home.dj.engine.rule

import home.dj.engine.model.DataEntity

class ${className} : BusinessRule {
    override val condition = { dataEntity: DataEntity -> true }
    override val action = { println("Executed some action based on $this.javaClass.simpleName") }
}