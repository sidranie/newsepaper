package fr.sidranie.newsepaper.dtos.person

data class RequestCreatePersonDto(
    val identifier: String,
    val email: String,
    val password: String,
    val givenName: String,
    val familyName: String,
    val isPublisher: Boolean = false,
)
