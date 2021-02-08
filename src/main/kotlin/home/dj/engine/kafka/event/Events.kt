package home.dj.engine.kafka.event

import java.sql.Timestamp

sealed class BaseEvent {
    abstract val timestamp: Timestamp
    abstract val id: Long
}

data class CompanyDataUpdatedEvent(
    val companyDataDTO: CompanyDataDTO,
    override val timestamp: Timestamp,
    override val id: Long
) : BaseEvent()