package fr.sidranie.newsepaper.controllers

import fr.sidranie.newsepaper.entities.Person
import fr.sidranie.newsepaper.exceptions.NotFoundException
import fr.sidranie.newsepaper.services.PersonService
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
    fun getAll() = ResponseEntity.ok<Iterable<Person>>(service.findAllPeople())

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long): ResponseEntity<Person> {
        val user = service.findPersonById(id)

        return if (user != null)
            ResponseEntity.ok<Person>(user)
        else
            ResponseEntity.notFound().build<Person>()
    }

    @PostMapping
    fun createPerson(@RequestBody toCreate: Person): ResponseEntity<Person> {
        val person = toCreate.copy(id=null)
        service.createPerson(person)
        return ResponseEntity.created(URI("/people/${person.id}")).body(person)
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable("id") id: Long): ResponseEntity<Person> {
        service.deletePersonById(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}")
    fun updatePerson(@PathVariable("id") id: Long, @RequestBody updates: Person): ResponseEntity<Person> {
        try {
            val person = service.patchPerson(id, updates)
            return ResponseEntity.ok(person)
        } catch (_: NotFoundException) {
            return ResponseEntity.notFound().build<Person>()
        }
    }
}
