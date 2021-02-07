package home.dj.engine.kafka.event

import java.sql.Timestamp

abstract class BaseEvent {
    abstract val timestamp: Timestamp
    abstract val id: Long
}