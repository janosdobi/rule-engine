package home.dj.engine.kafka.event

import home.dj.engine.model.DataEntity
import home.dj.engine.model.Company
import java.sql.Timestamp

sealed class BaseEvent {
    abstract val timestamp: Timestamp
    abstract val id: Long
    abstract val entity: DataEntity
    abstract fun getAllEntities(): Collection<DataEntity>
}

data class CompanyDataUpdatedEvent(
    override val entity: Company,
    override val timestamp: Timestamp,
    override val id: Long
) : BaseEvent() {
    override fun getAllEntities(): Collection<DataEntity> {
        //TODO solve hardcoding
        return mutableListOf(entity, entity.financialData)
    }
}