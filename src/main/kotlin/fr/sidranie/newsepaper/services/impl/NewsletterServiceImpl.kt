package fr.sidranie.newsepaper.services.impl

import fr.sidranie.newsepaper.dtos.newsletter.NewsletterDto
import fr.sidranie.newsepaper.dtos.newsletter.RequestCreateNewsletterDto
import fr.sidranie.newsepaper.dtos.newsletter.RequestUpdateNewsletterDto
import fr.sidranie.newsepaper.dtos.person.PersonDto
import fr.sidranie.newsepaper.entities.Newsletter
import fr.sidranie.newsepaper.entities.Person
import fr.sidranie.newsepaper.exceptions.NotFoundException
import fr.sidranie.newsepaper.repositories.NewsletterRepository
import fr.sidranie.newsepaper.services.NewsletterService
import fr.sidranie.newsepaper.services.PersonService
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class NewsletterServiceImpl(
    private val repository: NewsletterRepository,
    @Lazy private val personService: PersonService
): NewsletterService {
    override fun findAllNewsletters(withSubscribers: Boolean?): List<NewsletterDto> {
        val newsletters = repository.findAll()

        val mappedNewsletters =  newsletters.map {
            it.subscriptions = emptySet()
            NewsletterDto(it)
        }

        if (withSubscribers != null && withSubscribers) {
            mappedNewsletters.forEach {
                val subscribers = personService.findSubscribersForNewsletter(it.id!!)
                it.subscriptions = subscribers.map { subscriber: Person -> PersonDto(subscriber) }.toSet()
            }
        }

        return mappedNewsletters
    }

    override fun findNewsletterById(id: Long): Newsletter? = repository.findById(id).orElse(null)

    override fun createNewsletter(toCreate: RequestCreateNewsletterDto): Newsletter {
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
            subscriptions = emptySet(),
        )

        return repository.save<Newsletter>(newsletter)
    }

    override fun deleteNewsletterById(id: Long) = repository.deleteById(id)

    override fun patchNewsletter(id: Long, updates: RequestUpdateNewsletterDto): Newsletter {
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

        return repository.save(newsletter)
    }

    override fun findAllSubscribedNewsletters(personId: Long): List<Newsletter> = repository.findBySubscriptions_Id(personId)
}
