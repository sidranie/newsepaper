package fr.sidranie.newsepaper.dtos.person

import fr.sidranie.newsepaper.dtos.newsletter.NewsletterDto
import fr.sidranie.newsepaper.entities.Person

data class PersonDto(
    val id: Long?,
    val identifier: String?,
    val email: String?,
    val password: String?,
    val givenName: String?,
    val familyName: String?,
    val isPublisher: Boolean?,
    val subscribedNewsletters: Set<NewsletterDto>? = null,
) {
    constructor(person: Person) : this(
        id = person.id,
        identifier = person.identifier,
        email = person.email,
        password = person.password,
        givenName = person.givenName,
        familyName = person.familyName,
        isPublisher = person.isPublisher,
        subscribedNewsletters = person.subscribedNewsletters?.map { NewsletterDto(it) }?.toSet(),
    )
}
