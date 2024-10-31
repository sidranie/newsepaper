package fr.sidranie.newsepaper.services

import fr.sidranie.newsepaper.entities.Newsletter
import fr.sidranie.newsepaper.exceptions.NotFoundException
import fr.sidranie.newsepaper.repositories.NewsletterRepository
import org.springframework.stereotype.Service

@Service
class NewsletterService(private val repository: NewsletterRepository) {
    fun findAllNewsletters(): List<Newsletter> = repository.findAll().toList()

    fun findNewsletterById(id: Long): Newsletter? = repository.findById(id).orElse(null)

    fun createNewsletter(newsletter: Newsletter) {
        repository.save<Newsletter>(newsletter)
    }

    fun deleteNewsletterById(id: Long) = repository.deleteById(id)

    fun patchNewsletter(id: Long, updates: Newsletter): Newsletter {
        var newsletter = findNewsletterById(id)
        if (newsletter == null) {
            throw NotFoundException()
        }

        if (updates.headline != null) {
            newsletter.headline = updates.headline
        }
        if (updates.abstract != null) {
            newsletter.abstract = updates.abstract
        }

        repository.save(newsletter)

        return newsletter
    }
}
