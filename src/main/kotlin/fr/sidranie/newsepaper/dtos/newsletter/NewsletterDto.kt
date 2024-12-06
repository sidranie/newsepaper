package fr.sidranie.newsepaper.dtos.newsletter

import fr.sidranie.newsepaper.dtos.person.FullPersonDto
import fr.sidranie.newsepaper.entities.Newsletter

data class NewsletterDto(
    var id: Long?,
    var headline: String?,
    var abstract: String?,
    var publisher: FullPersonDto?,
) {
    constructor(newsletter: Newsletter) : this(
        id = newsletter.id,
        headline = newsletter.headline,
        abstract = newsletter.abstract,
        publisher = FullPersonDto(newsletter.publisher),
    )
}
