package fr.sidranie.newsepaper.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "news")
data class News(
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Column(nullable = false, unique = true)
    var headline: String?,
    @Column(nullable = false)
    var newsBody: String?,
    @ManyToOne(optional = false)
    @JoinColumn(name = "newsletter_id")
    var newsletter: Newsletter?,
)
