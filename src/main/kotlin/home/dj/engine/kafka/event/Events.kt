package home.dj.engine.kafka.event

import home.dj.engine.model.Company
import home.dj.engine.model.DataEntity
import java.sql.Timestamp

sealed class BaseEvent {
    abstract val timestamp: Timestamp
    abstract val id: Long
    abstract val entity: DataEntity
}

data class CompanyDataUpdatedEvent(
    override val entity: Company,
    override val timestamp: Timestamp,
    override val id: Long
) : BaseEvent()