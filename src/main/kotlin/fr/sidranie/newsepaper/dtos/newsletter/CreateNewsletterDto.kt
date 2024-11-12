package fr.sidranie.newsepaper.dtos.newsletter

data class CreateNewsletterDto(
    val headline: String,
    val abstract: String,
    var publisherId: Long
)
