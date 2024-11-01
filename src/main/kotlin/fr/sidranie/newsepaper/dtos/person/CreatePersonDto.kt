package fr.sidranie.newsepaper.dtos.person

import fr.sidranie.newsepaper.entities.Person

data class CreatePersonDto(
    val identifier: String,
    val email: String,
    val password: String,
    val givenName: String,
    val familyName: String,
    val isPublisher: Boolean = false,
)

fun Person.Companion.fromCreatePersonDto(createPersonDto: CreatePersonDto) = Person(
    id = null,
    identifier = createPersonDto.identifier,
    email = createPersonDto.email,
    password = createPersonDto.password,
    givenName = createPersonDto.givenName,
    familyName = createPersonDto.familyName,
    isPublisher = createPersonDto.isPublisher,
)