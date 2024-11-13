package fr.sidranie.newsepaper.services

import fr.sidranie.newsepaper.dtos.news.RequestCreateNewsDto
import fr.sidranie.newsepaper.entities.News

interface NewsService {
    fun findAllNews(newsletterId: Long?): List<News>
    fun findNewsById(id: Long): News?
    fun createNews(toCreate: RequestCreateNewsDto): News
    fun deleteNews(id: Long)
    fun patchNews(id: Long, updates: News): News
}
