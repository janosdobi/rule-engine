package home.dj.engine.kafka.event

import java.time.LocalDate

data class CompanyDataDTO(
    val name: String,
    val registrationDate: LocalDate,
    val legalForm: String,
    val sbiCodes: Collection<String>,
    val addressDTO: AddressDTO,
    val financialDataDTO: FinancialDataDTO
)