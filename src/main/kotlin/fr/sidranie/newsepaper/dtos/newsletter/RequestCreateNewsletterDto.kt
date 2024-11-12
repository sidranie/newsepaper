package fr.sidranie.newsepaper.dtos.newsletter

data class RequestCreateNewsletterDto(
    val headline: String,
    val abstract: String,
    var publisherId: Long
)
