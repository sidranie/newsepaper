package fr.sidranie.newsepaper.dtos.subscription

import fr.sidranie.newsepaper.dtos.newsletter.ShortNewsletterDto
import fr.sidranie.newsepaper.entities.Subscription
import java.time.Instant

class NewsletterSubscriptionDto(
    var since: Instant,
    var newsletter: ShortNewsletterDto,
) {
    constructor(subscription: Subscription) : this(
        since = subscription.since,
        newsletter = ShortNewsletterDto(subscription.newsletter),
    )
}
