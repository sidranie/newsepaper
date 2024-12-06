package fr.sidranie.newsepaper.entities

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "subscription")
data class Subscription(
    @EmbeddedId
    var id: SubscriptionId,
    @Column
    var since: Instant = Instant.now(),
)

@Embeddable
data class SubscriptionId(
    @Column
    var personId: Long,
    @Column
    var newsletterId: Long,
)
