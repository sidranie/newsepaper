package fr.sidranie.newsepaper.services

import fr.sidranie.newsepaper.dtos.person.RequestCreatePersonDto
import fr.sidranie.newsepaper.dtos.person.RequestUpdatePersonDto
import fr.sidranie.newsepaper.dtos.person.fromCreatePersonDto
import fr.sidranie.newsepaper.entities.Person
import fr.sidranie.newsepaper.exceptions.NotFoundException
import fr.sidranie.newsepaper.repositories.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(private val repository: PersonRepository) {

    fun findAllPeople(isPublisher: Boolean?): List<Person> {
        val peopleIterable =  if (isPublisher == null) repository.findAll()
        else repository.findAllByIsPublisher(isPublisher)
        return peopleIterable.toList()
    }

    fun findPersonById(id: Long): Person? = repository.findById(id).orElse(null)
    
    fun createPerson(toCreate: RequestCreatePersonDto): Person {
        val person = Person(
            id = null,
            identifier = toCreate.identifier,
            email = toCreate.email,
            password = toCreate.password,
            givenName = toCreate.givenName,
            familyName = toCreate.familyName,
            isPublisher = toCreate.isPublisher,
        )
        return repository.save<Person>(person)
    }

    fun deletePersonById(id: Long) = repository.deleteById(id)

    fun patchPerson(id: Long, updates: RequestUpdatePersonDto): Person {
        val person = findPersonById(id) ?: throw NotFoundException()
        person.applyUpdates(updates)
        return repository.save(person)
    }

    private fun Person.applyUpdates(updates: RequestUpdatePersonDto) {
        if (updates.identifier != null) {
            identifier = updates.identifier
        }
        if (updates.email != null) {
            email = updates.email
        }
        if (updates.givenName != null) {
            givenName = updates.givenName
        }
        if (updates.familyName != null) {
            familyName = updates.familyName
        }
        if (updates.isPublisher != null) {
            isPublisher = updates.isPublisher
        }
    }
}
