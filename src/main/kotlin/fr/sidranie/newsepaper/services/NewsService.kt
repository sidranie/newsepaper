package fr.sidranie.newsepaper.services

import fr.sidranie.newsepaper.entities.News
import fr.sidranie.newsepaper.exceptions.NotFoundException
import fr.sidranie.newsepaper.repositories.NewsRepository
import org.springframework.stereotype.Service

@Service
class NewsService(private val repository: NewsRepository) {

    fun findAllNews(newsletterId: Long?): List<News> {
        println(newsletterId)
        val newsIterable =  if (newsletterId == null) repository.findAll()
            else repository.findAllByNewsletterId(newsletterId)
        return newsIterable.toList()
    }

    fun findNewsById(id: Long): News? =
        repository.findById(id).orElse(null)

    fun createNews(news: News) {
        repository.save<News>(news)
    }

    fun deleteNews(id: Long) = repository.deleteById(id)

    fun patchNews(id: Long, updates: News): News {
        var news = findNewsById(id)
        if (news == null) {
            throw NotFoundException()
        }

        if (updates.headline != null) {
            news.headline = updates.headline
        }
        if (updates.newsBody != null) {
            news.newsBody = updates.newsBody
        }

        repository.save(news)

        return news
    }
}