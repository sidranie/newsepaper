package fr.sidranie.newsepaper.services

import fr.sidranie.newsepaper.entities.Person
import fr.sidranie.newsepaper.exceptions.NotFoundException
import fr.sidranie.newsepaper.repositories.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(private val repository: PersonRepository) {

    fun findAllPeople(isPublisher: Boolean?): Iterable<Person> {
        var people = repository.findAll()

        if (isPublisher != null) {
            people = people.filter { it.isPublisher == isPublisher }
        }

        return people
    }

    fun findPersonById(id: Long): Person? = repository.findById(id).orElse(null)
    
    fun createPerson(person: Person) {
        repository.save<Person>(person)
    }

    fun deletePersonById(id: Long) = repository.deleteById(id)

    fun patchPerson(id: Long, updates: Person): Person {
        var person = findPersonById(id)
        if (person == null) {
            throw NotFoundException()
        }

        if (updates.identifier != null) {
            person.identifier = updates.identifier
        }
        if (updates.email != null) {
            person.email = updates.email
        }
        if (updates.password != null) {
            person.password = updates.password
        }
        if (updates.givenName != null) {
            person.givenName = updates.givenName
        }
        if (updates.familyName != null) {
            person.familyName = updates.familyName
        }

        repository.save(person)

        return person
    }
}
