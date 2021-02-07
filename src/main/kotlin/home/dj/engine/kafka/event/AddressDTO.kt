package home.dj.engine.kafka.event

data class AddressDTO(
    val country: String,
    val town: String,
    val address: String,
    val number: Int
)
