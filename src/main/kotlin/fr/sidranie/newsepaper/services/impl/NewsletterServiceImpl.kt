package fr.sidranie.newsepaper.services.impl

import fr.sidranie.newsepaper.dtos.newsletter.FullNewsletterDto
import fr.sidranie.newsepaper.dtos.newsletter.RequestCreateNewsletterDto
import fr.sidranie.newsepaper.dtos.newsletter.RequestUpdateNewsletterDto
import fr.sidranie.newsepaper.dtos.newsletter.ShortNewsletterDto
import fr.sidranie.newsepaper.entities.Newsletter
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
    override fun findAllNewsletters(): List<ShortNewsletterDto> = repository.findAll().map { ShortNewsletterDto(it)}

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
            followers = TODO(),
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
}
