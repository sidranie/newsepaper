package fr.sidranie.newsepaper.services.impl

import fr.sidranie.newsepaper.dtos.person.RequestCreatePersonDto
import fr.sidranie.newsepaper.dtos.person.RequestUpdatePersonDto
import fr.sidranie.newsepaper.dtos.person.ShortPersonDto
import fr.sidranie.newsepaper.entities.Person
import fr.sidranie.newsepaper.exceptions.NotFoundException
import fr.sidranie.newsepaper.repositories.PersonRepository
import fr.sidranie.newsepaper.services.PersonService
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(private val repository: PersonRepository): PersonService {

    override fun findAllPeople(isPublisher: Boolean?): List<ShortPersonDto> {
        val people: Set<Person> =  if (isPublisher == null) repository.findAll()
        else repository.findAllByIsPublisher(isPublisher)

        val mappedPeople = people.map {
                ShortPersonDto(it)
            }

        return mappedPeople
    }

    override fun findPersonById(id: Long): Person? = repository.findById(id).orElse(null)
    
    override fun createPerson(toCreate: RequestCreatePersonDto): Person {
        val person = Person(
            id = null,
            identifier = toCreate.identifier,
            email = toCreate.email,
            password = toCreate.password,
            givenName = toCreate.givenName,
            familyName = toCreate.familyName,
            isPublisher = toCreate.isPublisher,
            subscribedNewsletters = emptySet(),
        )
        return repository.save<Person>(person)
    }

    override fun deletePersonById(id: Long) = repository.deleteById(id)

    override fun patchPerson(id: Long, updates: RequestUpdatePersonDto): Person {
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
