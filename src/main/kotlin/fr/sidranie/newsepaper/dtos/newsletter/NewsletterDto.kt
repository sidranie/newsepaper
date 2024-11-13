package fr.sidranie.newsepaper.dtos.newsletter

import fr.sidranie.newsepaper.dtos.person.PersonDto
import fr.sidranie.newsepaper.entities.Newsletter

data class NewsletterDto(
    val id: Long?,
    val headline: String?,
    val abstract: String?,
    val publisher: PersonDto?,
    val subscriptions: List<PersonDto>?,
) {
    constructor(newsletter: Newsletter) : this(
        id = newsletter.id,
        headline = newsletter.headline,
        abstract = newsletter.abstract,
        publisher = PersonDto(newsletter.publisher),
        subscriptions = newsletter.subscriptions?.map { PersonDto(it) }?.toList(),
    )
}
