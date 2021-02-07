package home.dj.engine.kafka.event

data class FinancialDataDTO(
    val totalAssets: Double,
    val equity: Double,
    val revenue: Double,
    val profit: Double,
    val ebitda: Double
)
