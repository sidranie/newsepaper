package fr.sidranie.newsepaper.dtos.person

data class RequestUpdatePersonDto (
    val identifier: String?,
    val email: String?,
    val givenName: String?,
    val familyName: String?,
    val isPublisher: Boolean?,
)
