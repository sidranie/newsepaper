package fr.sidranie.newsepaper.controllers

import fr.sidranie.newsepaper.entities.News
import fr.sidranie.newsepaper.services.NewsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/news")
class NewsController(private val service: NewsService) {

    @GetMapping
    fun getAllNews(): ResponseEntity<Iterable<News>> =
        ResponseEntity.ok(service.findAllNews())

    @GetMapping("/{id}")
    fun getNewsById(@PathVariable("id") id: Long): ResponseEntity<News> {
        val news = service.findNewsById(id)

        return if (news != null)
            ResponseEntity.ok(news)
        else
            ResponseEntity.notFound().build<News>()
    }
}