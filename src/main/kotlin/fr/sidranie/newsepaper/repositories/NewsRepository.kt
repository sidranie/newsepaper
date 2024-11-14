package fr.sidranie.newsepaper.repositories

import fr.sidranie.newsepaper.entities.News
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NewsRepository: CrudRepository<News, Long> {
    override fun findAll(): Set<News>
    fun findAllByNewsletterId(newsletterId: Long): Set<News>
}
