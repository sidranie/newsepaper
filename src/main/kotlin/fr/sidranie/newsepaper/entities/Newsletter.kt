package fr.sidranie.newsepaper.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "newsletter")
data class Newsletter(
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Column(nullable = false, unique = true)
    var headline: String?,
    @Column
    var abstract: String?,
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    var publisher: Person,
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subscribedNewsletters")
    var subscriptions: List<Person>?,
)
