package fr.sidranie.newsepaper.services

import fr.sidranie.newsepaper.dtos.newsletter.CreateNewsletterDto
import fr.sidranie.newsepaper.dtos.newsletter.UpdateNewsletterDto
import fr.sidranie.newsepaper.dtos.person.UpdatePersonDto
import fr.sidranie.newsepaper.entities.Newsletter
import fr.sidranie.newsepaper.entities.Person
import fr.sidranie.newsepaper.exceptions.NotFoundException
import fr.sidranie.newsepaper.repositories.NewsletterRepository
import org.springframework.stereotype.Service

@Service
class NewsletterService(private val repository: NewsletterRepository, private val personService: PersonService) {
    fun findAllNewsletters(): List<Newsletter> = repository.findAll().toList()

    fun findNewsletterById(id: Long): Newsletter? = repository.findById(id).orElse(null)

    fun createNewsletter(toCreate: CreateNewsletterDto): Newsletter {
        val publisher = personService.findPersonById(toCreate.publisherId)
        if (publisher == null) {
            throw IllegalStateException("Publisher not found")
        }
        if (!publisher.isPublisher!!) {
            throw IllegalStateException("Person is not a publisher")
        }

        val newsletter = Newsletter(
            id = null,
            headline = toCreate.headline,
            abstract = toCreate.abstract,
            publisher = publisher,
        )
        repository.save<Newsletter>(newsletter)
        return newsletter
    }

    fun deleteNewsletterById(id: Long) = repository.deleteById(id)

    fun patchNewsletter(id: Long, updates: UpdateNewsletterDto): Newsletter {
        val newsletter = findNewsletterById(id) ?: throw NotFoundException()

        if (updates.headline != null) {
            newsletter.headline = updates.headline
        }
        if (updates.abstract != null) {
            newsletter.abstract = updates.abstract
        }
        if (updates.publisherId != null) {
            val publisher = personService.findPersonById(updates.publisherId)
            if (publisher == null) {
                throw NotFoundException()
            } else {
                newsletter.publisher = publisher
            }
        }

        repository.save(newsletter)

        return newsletter
    }
}
