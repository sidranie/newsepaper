package fr.sidranie.newsepaper.entities

import jakarta.persistence.*

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
    var isPublisher: Boolean? = false,
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    var subscriptions: MutableSet<Subscription> = mutableSetOf()
)
