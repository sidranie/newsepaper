package fr.sidranie.newsepaper.repositories

import fr.sidranie.newsepaper.entities.News
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NewsRepository: CrudRepository<News, Long> {
    fun findAllByNewsletterId(newsletterId: Long): Iterable<News>
}
