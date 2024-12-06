package fr.sidranie.newsepaper.controllers

import fr.sidranie.newsepaper.dtos.newsletter.FullNewsletterDto
import fr.sidranie.newsepaper.dtos.newsletter.RequestCreateNewsletterDto
import fr.sidranie.newsepaper.dtos.newsletter.RequestUpdateNewsletterDto
import fr.sidranie.newsepaper.dtos.newsletter.ShortNewsletterDto
import fr.sidranie.newsepaper.entities.Newsletter
import fr.sidranie.newsepaper.exceptions.NotFoundException
import fr.sidranie.newsepaper.services.NewsletterService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/newsletters")
class NewsletterController(private val service: NewsletterService) {

    @GetMapping
    fun getAllNewsletters(): ResponseEntity<List<ShortNewsletterDto>> =
        ResponseEntity.ok(service.findAllNewsletters())

    @GetMapping("/{id}")
    fun getNewsletterById(@PathVariable("id") id: Long): ResponseEntity<FullNewsletterDto> {
        val newsletter = service.findFullNewsletterDtoById(id)

        return if (newsletter != null)
            ResponseEntity.ok(newsletter)
        else
            ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createNewsletter(@RequestBody toCreate: RequestCreateNewsletterDto): ResponseEntity<Newsletter> {
        var newsletter: Newsletter? = null
        try {
            newsletter = service.createNewsletter(toCreate)
        } catch (_: IllegalStateException) {
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity.created(URI("/newsletters/${newsletter.id}")).body(newsletter)
    }

    @DeleteMapping("/{id}")
    fun deleteNewsletter(@PathVariable("id") id: Long): ResponseEntity<Newsletter> {
        service.deleteNewsletterById(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}")
    fun patchNewsletter(@PathVariable("id") id: Long, @RequestBody updates: RequestUpdateNewsletterDto): ResponseEntity<Newsletter> {
        try {
            val newsletter = service.patchNewsletter(id, updates)
            return ResponseEntity.ok(newsletter)
        } catch (_: NotFoundException) {
            return ResponseEntity.notFound().build()
        }
    }
}
