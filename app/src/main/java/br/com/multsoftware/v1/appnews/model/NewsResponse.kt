package br.com.multsoftware.v1.appnews.model

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)