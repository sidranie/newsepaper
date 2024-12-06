package fr.sidranie.newsepaper.dtos.newsletter

import fr.sidranie.newsepaper.dtos.person.ShortPersonDto
import fr.sidranie.newsepaper.entities.Newsletter

data class FullNewsletterDto(
    var id: Long?,
    var headline: String?,
    var abstract: String?,
    var publisher: ShortPersonDto?,
) {
    constructor(newsletter: Newsletter) : this(
        id = newsletter.id,
        headline = newsletter.headline,
        abstract = newsletter.abstract,
        publisher = ShortPersonDto(newsletter.publisher),
    )
}
