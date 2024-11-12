package fr.sidranie.newsepaper.dtos.news

data class RequestCreateNewsDto(
    val headline: String,
    val newsBody: String?,
    val newsletterId: Long,
)
