package fr.sidranie.newsepaper.services.impl

import fr.sidranie.newsepaper.dtos.news.RequestCreateNewsDto
import fr.sidranie.newsepaper.entities.News
import fr.sidranie.newsepaper.exceptions.NotFoundException
import fr.sidranie.newsepaper.repositories.NewsRepository
import fr.sidranie.newsepaper.services.NewsService
import fr.sidranie.newsepaper.services.NewsletterService
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class NewsServiceImpl(
    private val repository: NewsRepository,
    private val newsletterService: NewsletterService,
): NewsService {

    override fun findAllNews(newsletterId: Long?): List<News> = if (newsletterId == null) repository.findAll().toList()
        else repository.findAllByNewsletterId(newsletterId).toList()

    override fun findNewsById(id: Long): News? =
        repository.findById(id).orElse(null)

    override fun createNews(toCreate: RequestCreateNewsDto): News {
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

        return repository.save<News>(news)
    }

    override fun deleteNews(id: Long) = repository.deleteById(id)

    override fun patchNews(id: Long, updates: News): News {
        val news = findNewsById(id) ?: throw NotFoundException()

        if (updates.headline != null) {
            news.headline = updates.headline
        }
        if (updates.newsBody != null) {
            news.newsBody = updates.newsBody
        }

        news.updatedAt = Instant.now()

        return repository.save(news)
    }
}
