package fr.sidranie.newsepaper.controllers

import fr.sidranie.newsepaper.entities.Person
import fr.sidranie.newsepaper.repositories.PersonRepository
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
class PersonController(val repository: PersonRepository) {

    @GetMapping
    fun getAll() = ResponseEntity.ok(repository.findAll())

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long): ResponseEntity<Person> {
        val user = repository.findById(id)

        if (user.isPresent) {
            return ResponseEntity.ok(user.get())
        } else {
            return ResponseEntity.notFound().build<Person>()
        }
    }

    @PostMapping
    fun createPerson(@RequestBody toCreate: Person): ResponseEntity<Person> {
        val person = toCreate.copy(id=null)
        repository.save<Person>(person)
        return ResponseEntity.created(URI("/people/${person.id}")).body(person)
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable("id") id: Long): ResponseEntity<Person> {
        repository.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}")
    fun updatePerson(@PathVariable("id") id: Long, @RequestBody updates: Person): ResponseEntity<Person> {
        val gotPerson = repository.findById(id)
        if (gotPerson.isEmpty()) {
            return ResponseEntity.notFound().build<Person>()
        }

        var person = gotPerson.get()

        if (updates.identifier != null) {
            person.identifier = updates.identifier
        }
        if (updates.email != null) {
            person.email = updates.email
        }
        if (updates.password != null) {
            person.password = updates.password
        }
        if (updates.givenName != null) {
            person.givenName = updates.givenName
        }
        if (updates.familyName != null) {
            person.familyName = updates.familyName
        }

        repository.save(person)

        return ResponseEntity.ok(person)
    }
}