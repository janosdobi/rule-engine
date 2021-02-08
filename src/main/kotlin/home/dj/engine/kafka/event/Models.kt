package home.dj.engine.kafka.event

import java.time.LocalDate

data class CompanyDataDTO(
    val name: String,
    val registrationDate: LocalDate,
    val legalForm: String,
    val sbiCodes: Collection<String>,
    val address: AddressDTO,
    val financialData: FinancialDataDTO
)

data class FinancialDataDTO(
    val totalAssets: Double,
    val equity: Double,
    val revenue: Double,
    val profit: Double,
    val ebitda: Double
)

data class AddressDTO(
    val country: String,
    val town: String,
    val address: String,
    val number: Int
)