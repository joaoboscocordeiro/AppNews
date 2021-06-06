package br.com.multsoftware.v1.appnews.view

import br.com.multsoftware.v1.appnews.R
import br.com.multsoftware.v1.appnews.adapter.MainAdapter
import br.com.multsoftware.v1.appnews.model.Article
import br.com.multsoftware.v1.appnews.model.data.NewsDataSource
import br.com.multsoftware.v1.appnews.presenter.ViewHome
import br.com.multsoftware.v1.appnews.presenter.news.NewsPresenter

class MainActivity : AbstractActivity(), ViewHome.View {

    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var presenter: NewsPresenter

    override fun getLayout(): Int = R.layout.activity_main

    override fun onInject() {

        val dataSource = NewsDataSource()
        presenter = NewsPresenter(this, dataSource)
        presenter.requestAll()
    }

    override fun showProgressBar() {
        TODO("Not yet implemented")
    }

    override fun showFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun hideProgressBar() {
        TODO("Not yet implemented")
    }

    override fun showArticles(articles: List<Article>) {
        TODO("Not yet implemented")
    }
}
