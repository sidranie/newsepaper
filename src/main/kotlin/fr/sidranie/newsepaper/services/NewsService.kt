package fr.sidranie.newsepaper.services

import fr.sidranie.newsepaper.dtos.news.CreateNewsDto
import fr.sidranie.newsepaper.entities.News
import fr.sidranie.newsepaper.exceptions.NotFoundException
import fr.sidranie.newsepaper.repositories.NewsRepository
import fr.sidranie.newsepaper.repositories.NewsletterRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class NewsService(
    private val repository: NewsRepository,
    private val newsletterService: NewsletterService,
) {

    fun findAllNews(newsletterId: Long?): List<News> {
        val newsIterable =  if (newsletterId == null) repository.findAll()
            else repository.findAllByNewsletterId(newsletterId)
        return newsIterable.toList()
    }

    fun findNewsById(id: Long): News? =
        repository.findById(id).orElse(null)

    fun createNews(toCreate: CreateNewsDto): News {
        val newsletter = newsletterService.findNewsletterById(toCreate.newsletterId)
        if (newsletter == null) {
            throw NotFoundException()
        }

        val news = News(
            id = null,
            headline = toCreate.headline,
            newsBody = toCreate.newsBody,
            newsletter = newsletter,
            createdAt = Instant.now(),
            updatedAt = null,
        )
        repository.save<News>(news)
        return news
    }

    fun deleteNews(id: Long) = repository.deleteById(id)

    fun patchNews(id: Long, updates: News): News {
        val news = findNewsById(id) ?: throw NotFoundException()

        if (updates.headline != null) {
            news.headline = updates.headline
        }
        if (updates.newsBody != null) {
            news.newsBody = updates.newsBody
        }

        news.updatedAt = Instant.now()
        repository.save(news)

        return news
    }
}
