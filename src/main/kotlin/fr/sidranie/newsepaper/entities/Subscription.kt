package fr.sidranie.newsepaper.entities

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "subscription")
data class Subscription(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    var person: Person,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "newsletter_id", nullable = false)
    var newsletter: Newsletter,
    @Column(nullable = false)
    var since: Instant = Instant.now(),
)
