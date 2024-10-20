package fr.sidranie.newsepaper.services

import fr.sidranie.newsepaper.entities.News
import fr.sidranie.newsepaper.repositories.NewsRepository
import org.springframework.stereotype.Service

@Service
class NewsService(private val repository: NewsRepository) {
    fun findAllNews(): Iterable<News> = repository.findAll()
    fun findNewsById(id: Long): News? = repository.findById(id).orElse(null)
}