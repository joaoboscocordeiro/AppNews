package br.com.multsoftware.v1.appnews.repository

import androidx.lifecycle.LiveData
import br.com.multsoftware.v1.appnews.data.local.db.ArticleDatabase
import br.com.multsoftware.v1.appnews.data.local.model.Article
import br.com.multsoftware.v1.appnews.data.remote.NewsAPI

/**
 * Created by João Bosco on 07/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
class NewsRepository(
    private val api: NewsAPI,
    private val db: ArticleDatabase
) {

    // Remote
    suspend fun getAllRemote() = api.getBreakingNews()
    suspend fun search(query: String) = api.searchNews(query)

    // Local
    fun getAll(): LiveData<List<Article>> = db.getArticleDao().getAll()
    suspend fun updateInsert(article: Article) = db.getArticleDao().updateInsert(article)
    suspend fun delete(article: Article) = db.getArticleDao().delete(article)
}