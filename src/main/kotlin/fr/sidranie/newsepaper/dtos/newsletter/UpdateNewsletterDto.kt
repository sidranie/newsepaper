package fr.sidranie.newsepaper.dtos.newsletter

data class UpdateNewsletterDto(
    val headline: String?,
    var abstract: String?,
    val publisherId: Long?,
)
