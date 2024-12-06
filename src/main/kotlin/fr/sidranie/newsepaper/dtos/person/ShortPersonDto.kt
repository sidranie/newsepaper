package fr.sidranie.newsepaper.dtos.person

import fr.sidranie.newsepaper.entities.Person

data class ShortPersonDto(
    var id: Long?,
    var identifier: String?,
) {
    constructor(person: Person) : this(
        id = person.id,
        identifier = person.identifier,
    )
}
