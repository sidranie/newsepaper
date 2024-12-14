package fr.sidranie.newsepaper.entities

import jakarta.persistence.*

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
    @OneToMany(fetch = FetchType.LAZY)
    var subscriptions: Set<Subscription> = emptySet(),
)
