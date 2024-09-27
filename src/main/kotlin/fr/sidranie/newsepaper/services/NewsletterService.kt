package fr.sidranie.newsepaper.services

import fr.sidranie.newsepaper.entities.Newsletter
import fr.sidranie.newsepaper.repositories.NewsletterRepository
import org.springframework.stereotype.Service

@Service
class NewsletterService(private val repository: NewsletterRepository) {
    fun findAllNewsletters(): Iterable<Newsletter> = repository.findAll()
    fun findNewsletterById(id: Long): Newsletter? = repository.findById(id).orElse(null)
    fun createNewsletter(newsletter: Newsletter) {
        repository.save<Newsletter>(newsletter)
    }
}
