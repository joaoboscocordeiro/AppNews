package br.com.multsoftware.v1.appnews.presenter.favorite

import br.com.multsoftware.v1.appnews.model.Article
import br.com.multsoftware.v1.appnews.model.data.NewsDataSource
import br.com.multsoftware.v1.appnews.presenter.ViewHome

/**
 * Created by João Bosco on 07/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

class FavoritePresenter(
    val view: ViewHome.Favorite,
    private val dataSource: NewsDataSource
) : FavoriteHome.Presenter {

    fun getAll() {
        this.dataSource.getAllArticle(this)
    }

    fun saveArticle(article: Article) {
        this.dataSource.saveArticle(article)
    }

    fun deleteArticle(article: Article) {
        this.dataSource.deleteArticle(article)
    }

    override fun onSuccess(articles: List<Article>) {
        this.view.showArticles(articles)
    }
}