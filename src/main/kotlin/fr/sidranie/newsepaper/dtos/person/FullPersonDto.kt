package fr.sidranie.newsepaper.dtos.person

import fr.sidranie.newsepaper.dtos.subscription.NewsletterSubscriptionDto
import fr.sidranie.newsepaper.entities.Person

data class FullPersonDto(
    var id: Long?,
    var identifier: String?,
    var email: String?,
    var password: String?,
    var givenName: String?,
    var familyName: String?,
    var isPublisher: Boolean?,
    var subscriptions: Set<NewsletterSubscriptionDto>? = null,
) {
    constructor(person: Person) : this(
        id = person.id,
        identifier = person.identifier,
        email = person.email,
        password = person.password,
        givenName = person.givenName,
        familyName = person.familyName,
        isPublisher = person.isPublisher,
        subscriptions = person.subscriptions.map { NewsletterSubscriptionDto(it) }.toSet(),
    )
}
