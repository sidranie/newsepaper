package fr.sidranie.newsepaper.controllers

import fr.sidranie.newsepaper.entities.News
import fr.sidranie.newsepaper.exceptions.NotFoundException
import fr.sidranie.newsepaper.services.NewsService
import jakarta.websocket.server.PathParam
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/news")
class NewsController(private val service: NewsService) {

    @GetMapping
    fun getAllNews(@PathParam("newsletterId") newsletterId: Long?): ResponseEntity<List<News>> =
        ResponseEntity.ok(service.findAllNews(newsletterId))

    @GetMapping("/{id}")
    fun getNewsById(@PathVariable("id") id: Long): ResponseEntity<News> {
        val news = service.findNewsById(id)

        return if (news != null)
            ResponseEntity.ok(news)
        else
            ResponseEntity.notFound().build<News>()
    }

    @PostMapping
    fun createNews(@RequestBody toCreate: News): ResponseEntity<News> {
        val news = toCreate.copy(id = null)
        service.createNews(news)
        return ResponseEntity.created(URI("/news/${news.id}")).body(news)
    }

    @DeleteMapping("/{id}")
    fun deleteNews(@PathVariable("id") id: Long): ResponseEntity<News> {
        service.deleteNews(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("{id}")
    fun updateNews(@PathVariable("id") id: Long, @RequestBody updates: News): ResponseEntity<News> {
        try {
            val news = service.patchNews(id, updates)
            return ResponseEntity.ok(news)
        } catch (_: NotFoundException) {
            return ResponseEntity.notFound().build<News>()
        }
    }
}