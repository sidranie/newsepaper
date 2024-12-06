package fr.sidranie.newsepaper.dtos.person

import fr.sidranie.newsepaper.entities.Person

data class ShortPersonDto(
    var id: Long?,
    var identifier: String?,
    var email: String?,
    var givenName: String?,
    var familyName: String?,
) {
    constructor(person: Person) : this(
        id = person.id,
        identifier = person.identifier,
        email = person.email,
        givenName = person.givenName,
        familyName = person.familyName,
    )
}
