package home.dj.engine.kafka.event

import java.sql.Timestamp

data class CompanyDataUpdatedEvent(
    val companyDataDTO: CompanyDataDTO,
    override val timestamp: Timestamp,
    override val id: Long
) : BaseEvent()