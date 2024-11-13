package fr.sidranie.newsepaper.repositories

import fr.sidranie.newsepaper.entities.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: CrudRepository<Person, Long> {
    fun findAllByIsPublisher(isPublisher: Boolean): Iterable<Person>
    fun findBySubscribedNewsletters_Id(newsletterId: Long): List<Person>
}
