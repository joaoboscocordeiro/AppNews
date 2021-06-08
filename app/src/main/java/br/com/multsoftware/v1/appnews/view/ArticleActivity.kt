package br.com.multsoftware.v1.appnews.view

import android.webkit.WebViewClient
import br.com.multsoftware.v1.appnews.R
import br.com.multsoftware.v1.appnews.model.Article
import br.com.multsoftware.v1.appnews.model.data.NewsDataSource
import br.com.multsoftware.v1.appnews.presenter.ViewHome
import br.com.multsoftware.v1.appnews.presenter.favorite.FavoritePresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AbstractActivity(), ViewHome.Favorite {

    private lateinit var article: Article
    private lateinit var presenter: FavoritePresenter

    override fun getLayout(): Int = R.layout.activity_article

    override fun onInject() {
        getArticle()
        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this, dataSource)

        webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { url ->
                loadUrl(url)
            }
        }

        fab.setOnClickListener {
            presenter.saveArticle(article)
            Snackbar.make(
                it, R.string.article_saved_successful,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun getArticle() {
        intent.extras?.let { articleSend ->
            article = articleSend.get("article") as Article
        }
    }

    override fun showArticles(articles: List<Article>) {}
}
