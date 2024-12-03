package fr.sidranie.newsepaper.repositories

import fr.sidranie.newsepaper.entities.Newsletter
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NewsletterRepository: CrudRepository<Newsletter, Long> {
    override fun findAll(): Set<Newsletter>
}
