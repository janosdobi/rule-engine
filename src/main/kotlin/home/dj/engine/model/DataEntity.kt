package home.dj.engine.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import home.dj.engine.rule.BusinessRule
import com.fasterxml.jackson.annotation.JsonSubTypes.Type

//TODO this is not so good here
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY
)
@JsonSubTypes(
    Type(value = Company::class, name = "Company"),
    Type(value = Address::class, name = "Address"),
    Type(value = FinancialData::class, name = "FinancialData")
)
interface DataEntity {
    val getRuleEntityPairs: () -> Collection<Pair<DataEntity,BusinessRule>>
}