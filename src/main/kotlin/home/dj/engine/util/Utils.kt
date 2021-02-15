package home.dj.engine.util

import home.dj.engine.model.DataEntity
import kotlin.reflect.KProperty1
import kotlin.reflect.KType
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties


fun inlineAllMemberDataEntities(entity: DataEntity): Collection<DataEntity> {
    val listOfEmbeddedModelEntities = mutableListOf(entity)
    val dataEntityType = entity::class.supertypes.first()
    populateEntityListRecursive(entity, listOfEmbeddedModelEntities, dataEntityType)
    return listOfEmbeddedModelEntities
}

@Suppress("UNCHECKED_CAST")
private fun populateEntityListRecursive(
    entity: DataEntity,
    listOfEmbeddedModelEntities: MutableList<DataEntity>,
    dataEntityType: KType
) {
    entity::class.memberProperties
        .filter { it.returnType.isSubtypeOf(dataEntityType) }
        .forEach {
            it as KProperty1<Any, *>
            val embeddedEntity = it.get(entity)
            if (embeddedEntity is DataEntity) {
                listOfEmbeddedModelEntities.add(embeddedEntity)
                populateEntityListRecursive(embeddedEntity, listOfEmbeddedModelEntities, dataEntityType)
            }
        }
}
