package br.com.multsoftware.v1.appnews.model.data

import br.com.multsoftware.v1.appnews.model.Article
import br.com.multsoftware.v1.appnews.model.db.ArticleDatabase

/**
 * Created by João Bosco on 07/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

class NewsRepository(private val db: ArticleDatabase) {

    suspend fun updateInsert(article: Article) = db.getArticleDao().updateInsert(article)

    fun getAll(): List<Article> = db.getArticleDao().getAll()

    suspend fun delete(article: Article) = db.getArticleDao().delete(article)
}