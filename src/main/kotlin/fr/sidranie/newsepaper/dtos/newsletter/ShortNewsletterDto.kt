package fr.sidranie.newsepaper.dtos.newsletter

import fr.sidranie.newsepaper.entities.Newsletter

data class ShortNewsletterDto(
    var id: Long?,
    var headline: String?,
    var abstract: String?,
) {
    constructor(newsletter: Newsletter) : this(
        id = newsletter.id,
        headline = newsletter.headline,
        abstract = newsletter.abstract,
    )
}
