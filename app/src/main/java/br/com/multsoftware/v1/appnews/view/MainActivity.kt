package br.com.multsoftware.v1.appnews.view

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.multsoftware.v1.appnews.R
import br.com.multsoftware.v1.appnews.adapter.MainAdapter
import br.com.multsoftware.v1.appnews.model.Article
import br.com.multsoftware.v1.appnews.model.data.NewsDataSource
import br.com.multsoftware.v1.appnews.presenter.ViewHome
import br.com.multsoftware.v1.appnews.presenter.news.NewsPresenter
import kotlinx.android.synthetic.main.activity_main.*

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
        configRecycler()
    }

    private fun configRecycler() {
        with(rvNews) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity, DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun showProgressBar() {
        rvProgressBar.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        rvProgressBar.visibility = View.INVISIBLE
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }
}
