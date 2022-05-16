package br.com.multsoftware.v1.appnews.data.local.model

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)