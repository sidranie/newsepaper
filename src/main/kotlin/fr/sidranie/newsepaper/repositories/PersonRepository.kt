package fr.sidranie.newsepaper.repositories

import fr.sidranie.newsepaper.entities.Person
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : CrudRepository<Person, Long> {
    override fun findAll(): Set<Person>
    fun findAllByIsPublisher(isPublisher: Boolean): Set<Person>

    @Query("SELECT p FROM Person p LEFT JOIN FETCH p.subscriptions WHERE p.id = :id")
    fun findPersonWithSubscriptionsById(@Param("id") id: Long): Person?
}