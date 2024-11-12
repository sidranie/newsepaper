package fr.sidranie.newsepaper.dtos.news

data class CreateNewsDto(
    val headline: String,
    val newsBody: String?,
    val newsletterId: Long,
)
