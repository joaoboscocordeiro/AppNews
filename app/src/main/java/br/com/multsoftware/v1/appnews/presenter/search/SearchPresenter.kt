package br.com.multsoftware.v1.appnews.presenter.search

import br.com.multsoftware.v1.appnews.data.local.model.NewsResponse
import br.com.multsoftware.v1.appnews.repository.NewsDataSource
import br.com.multsoftware.v1.appnews.presenter.ViewHome

/**
 * Created by João Bosco on 05/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
class SearchPresenter(
    val view: ViewHome.View,
    private val dataSource: NewsDataSource
) : SearchHome.Presenter {

    override fun search(term: String) {
        this.view.showProgressBar()
        this.dataSource.searchNews(term, this)
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        this.view.showArticles(newsResponse.articles)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }
}