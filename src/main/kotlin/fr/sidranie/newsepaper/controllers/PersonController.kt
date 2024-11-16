package fr.sidranie.newsepaper.controllers

import fr.sidranie.newsepaper.dtos.ActionResult
import fr.sidranie.newsepaper.dtos.person.PersonDto
import fr.sidranie.newsepaper.dtos.person.RequestCreatePersonDto
import fr.sidranie.newsepaper.dtos.person.RequestUpdatePersonDto
import fr.sidranie.newsepaper.entities.Person
import fr.sidranie.newsepaper.exceptions.NotFoundException
import fr.sidranie.newsepaper.services.PersonService
import fr.sidranie.newsepaper.services.impl.PersonServiceImpl
import jakarta.websocket.server.PathParam
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/people")
class PersonController(private val service: PersonService) {

    @GetMapping
    fun getAll(
        @PathParam("isPublisher") isPublisher: Boolean?,
        @PathParam("withSubscriptions") withSubscriptions: Boolean?
    ) = ResponseEntity.ok<List<PersonDto>>(service.findAllPeople(isPublisher, withSubscriptions))

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long): ResponseEntity<Person> {
        val user = service.findPersonById(id)

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
    fun updatePerson(@PathVariable("id") id: Long, @RequestBody updates: RequestUpdatePersonDto): ResponseEntity<Person> {
        try {
            val person = service.patchPerson(id, updates)
            return ResponseEntity.ok(person)
        } catch (_: NotFoundException) {
            return ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/{personId}/newsletters/{newsletterId}")
    fun subscribeToNewsletter(@PathVariable("personId") personId: Long, @PathVariable("newsletterId") newsletterId: Long): ResponseEntity<ActionResult> {
        try {
            service.subscribeToNewsletter(personId, newsletterId)
            return ResponseEntity.ok(ActionResult("Person $personId follows the newsletter $newsletterId"))
        } catch (_: NotFoundException) {
            return ResponseEntity.badRequest().body(ActionResult("Person $personId or newsletter $newsletterId not found"))
        }
    }

    @DeleteMapping("/{personId}/newsletters/{newsletterId}")
    fun unsubscribeToNewsletter(@PathVariable("personId") personId: Long, @PathVariable("newsletterId") newsletterId: Long): ResponseEntity<ActionResult> {
        try {
            service.unsubscribeToNewsletter(personId, newsletterId)
            return ResponseEntity.ok(ActionResult("Person $personId does not follow the newsletter $newsletterId"))
        } catch (_: NotFoundException) {
            return ResponseEntity.badRequest().body(ActionResult("Person $personId or newsletter $newsletterId not found"))
        }
    }
}
