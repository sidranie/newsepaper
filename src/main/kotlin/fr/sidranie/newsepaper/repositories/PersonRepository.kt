package fr.sidranie.newsepaper.repositories

import fr.sidranie.newsepaper.entities.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: CrudRepository<Person, Long> {
    override fun findAll(): Set<Person>
    fun findAllByIsPublisher(isPublisher: Boolean): Set<Person>
    fun findBySubscribedNewsletters_Id(newsletterId: Long): List<Person>
}
