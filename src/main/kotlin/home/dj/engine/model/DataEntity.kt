package home.dj.engine.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo

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
    val getEntityRuleNamePairs: () -> Collection<Pair<DataEntity, String>>
}