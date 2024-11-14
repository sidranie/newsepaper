package fr.sidranie.newsepaper.dtos.newsletter

import fr.sidranie.newsepaper.dtos.person.PersonDto
import fr.sidranie.newsepaper.entities.Newsletter

data class NewsletterDto(
    var id: Long?,
    var headline: String?,
    var abstract: String?,
    var publisher: PersonDto?,
    var subscriptions: List<PersonDto>?,
) {
    constructor(newsletter: Newsletter) : this(
        id = newsletter.id,
        headline = newsletter.headline,
        abstract = newsletter.abstract,
        publisher = PersonDto(newsletter.publisher),
        subscriptions = newsletter.subscriptions?.map { PersonDto(it) }?.toList(),
    )
}
