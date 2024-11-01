package fr.sidranie.newsepaper.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "person")
data class Person(
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Column(nullable = false, unique = true)
    var identifier: String?,
    @Column(nullable = false, unique = true)
    var email: String?,
    @Column(nullable = false)
    var password: String?,
    @Column(nullable = false)
    var givenName: String?,
    @Column(nullable = false)
    var familyName: String?,
    @Column(nullable = false)
    var isPublisher: Boolean? = false
) {
    companion object
}
