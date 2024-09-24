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
    val id: Long,
    @Column(nullable = false, unique = true)
    val identifier: String,
    @Column(nullable = false, unique = true)
    val email: String,
    @Column(nullable = false)
    val password: String,
    @Column(nullable = false)
    val givenName: String,
    @Column(nullable = false)
    val familyName: String,
)
