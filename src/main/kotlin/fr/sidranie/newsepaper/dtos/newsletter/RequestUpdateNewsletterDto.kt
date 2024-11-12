package fr.sidranie.newsepaper.dtos.newsletter

data class RequestUpdateNewsletterDto(
    val headline: String?,
    var abstract: String?,
    val publisherId: Long?,
)
