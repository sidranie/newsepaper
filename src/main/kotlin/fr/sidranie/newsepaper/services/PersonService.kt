package fr.sidranie.newsepaper.services

import fr.sidranie.newsepaper.dtos.person.FullPersonDto
import fr.sidranie.newsepaper.dtos.person.RequestCreatePersonDto
import fr.sidranie.newsepaper.dtos.person.RequestUpdatePersonDto
import fr.sidranie.newsepaper.dtos.person.ShortPersonDto
import fr.sidranie.newsepaper.entities.Person

interface PersonService {
    fun findAllPeople(isPublisher: Boolean?): List<ShortPersonDto>
    fun findPersonById(id: Long): Person?
    fun findShortPersonDtoById(id: Long): ShortPersonDto?
    fun findFullPersonDtoById(id: Long): FullPersonDto?
    fun createPerson(toCreate: RequestCreatePersonDto): Person
    fun deletePersonById(id: Long)
    fun patchPerson(id: Long, updates: RequestUpdatePersonDto): Person
}
