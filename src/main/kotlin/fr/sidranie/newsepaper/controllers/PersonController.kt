package fr.sidranie.newsepaper.controllers

import fr.sidranie.newsepaper.dtos.person.FullPersonDto
import fr.sidranie.newsepaper.dtos.person.RequestCreatePersonDto
import fr.sidranie.newsepaper.dtos.person.RequestUpdatePersonDto
import fr.sidranie.newsepaper.dtos.person.ShortPersonDto
import fr.sidranie.newsepaper.entities.Person
import fr.sidranie.newsepaper.exceptions.NotFoundException
import fr.sidranie.newsepaper.services.PersonService
import jakarta.transaction.Transactional
import jakarta.websocket.server.PathParam
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/people")
class PersonController(private val service: PersonService) {

    @GetMapping
    fun getAll(@PathParam("isPublisher") isPublisher: Boolean?) =
        ResponseEntity.ok<List<ShortPersonDto>>(service.findAllPeople(isPublisher))

    @GetMapping("/{id}")
    @Transactional
    fun getById(@PathVariable("id") id: Long): ResponseEntity<FullPersonDto> {
        val person = service.findPersonById(id)
        person?.subscriptions?.size
        val user = if (person != null) FullPersonDto(person) else null

        return if (user != null)
            ResponseEntity.ok(user)
        else
            ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createPerson(@RequestBody toCreate: RequestCreatePersonDto): ResponseEntity<Person> {
        val person = service.createPerson(toCreate)
        return ResponseEntity.created(URI("/people/${person.id}")).body(person)
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable("id") id: Long): ResponseEntity<Person> {
        service.deletePersonById(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}")
    fun updatePerson(
        @PathVariable("id") id: Long,
        @RequestBody updates: RequestUpdatePersonDto
    ): ResponseEntity<Person> {
        try {
            val person = service.patchPerson(id, updates)
            return ResponseEntity.ok(person)
        } catch (_: NotFoundException) {
            return ResponseEntity.notFound().build()
        }
    }
}
