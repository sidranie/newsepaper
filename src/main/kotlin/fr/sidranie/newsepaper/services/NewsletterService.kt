package fr.sidranie.newsepaper.services

import fr.sidranie.newsepaper.dtos.newsletter.RequestCreateNewsletterDto
import fr.sidranie.newsepaper.dtos.newsletter.RequestUpdateNewsletterDto
import fr.sidranie.newsepaper.dtos.newsletter.ShortNewsletterDto
import fr.sidranie.newsepaper.entities.Newsletter

interface NewsletterService {
    fun findAllNewsletters(): List<ShortNewsletterDto>
    fun findNewsletterById(id: Long): Newsletter?
    fun createNewsletter(toCreate: RequestCreateNewsletterDto): Newsletter
    fun deleteNewsletterById(id: Long)
    fun patchNewsletter(id: Long, updates: RequestUpdateNewsletterDto): Newsletter
}
