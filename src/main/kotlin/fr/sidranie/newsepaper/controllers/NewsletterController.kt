package fr.sidranie.newsepaper.controllers

import fr.sidranie.newsepaper.entities.Newsletter
import fr.sidranie.newsepaper.services.NewsletterService
import fr.sidranie.newsepaper.services.PersonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/newsletters")
class NewsletterController(private val service: NewsletterService,
    private val personService: PersonService) {

    @GetMapping
    fun getAllNewsletters(): ResponseEntity<Iterable<Newsletter>> =
        ResponseEntity.ok(service.findAllNewsletters())

    @GetMapping("/{id}")
    fun getNewsletterById(@PathVariable("id") id: Long): ResponseEntity<Newsletter> {
        val newsletter = service.findNewsletterById(id)

        return if (newsletter != null)
            ResponseEntity.ok(newsletter)
        else
            ResponseEntity.notFound().build<Newsletter>()
    }

    @PostMapping
    fun createPerson(@RequestBody toCreate: Newsletter): ResponseEntity<Newsletter> {
        val newsletter = toCreate.copy(
            id=null,
            publisher = personService.findPersonById(1L)!!,
        )
        service.createNewsletter(newsletter)
        return ResponseEntity.created(URI("/newsletter/${newsletter.id}")).body(newsletter)
    }

}
