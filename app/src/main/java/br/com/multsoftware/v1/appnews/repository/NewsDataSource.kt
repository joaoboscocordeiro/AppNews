package br.com.multsoftware.v1.appnews.repository

import android.content.Context
import br.com.multsoftware.v1.appnews.data.local.db.ArticleDatabase
import br.com.multsoftware.v1.appnews.data.local.model.Article
import br.com.multsoftware.v1.appnews.data.remote.RetrofitInstance
import br.com.multsoftware.v1.appnews.presenter.favorite.FavoriteHome
import br.com.multsoftware.v1.appnews.presenter.news.NewsHome
import br.com.multsoftware.v1.appnews.presenter.search.SearchHome
import kotlinx.coroutines.*

/**
 * Created by João Bosco on 05/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
class NewsDataSource(context: Context) {

    private var db: ArticleDatabase = ArticleDatabase(context)
    private var newsRepository: NewsRepository = NewsRepository(RetrofitInstance.api, db)

    fun getBreakingNews(callback: NewsHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getBreakingNews("br")
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    callback.onSuccess(newsResponse)
                }
                callback.onComplete()
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }

    fun searchNews(term: String, callback: SearchHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.searchNews(term)
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    callback.onSuccess(newsResponse)
                }
                callback.onComplete()
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }

    fun saveArticle(article: Article) {
        GlobalScope.launch(Dispatchers.Main) {
            newsRepository.updateInsert(article)
        }
    }

    fun getAllArticle(callback: FavoriteHome.Presenter) {
        var allArticles: List<Article>
        CoroutineScope(Dispatchers.IO).launch {
            allArticles = newsRepository.getAll().value!!

            withContext(Dispatchers.Main) {
                callback.onSuccess(allArticles)
            }
        }
    }

    fun deleteArticle(article: Article?) {
        GlobalScope.launch(Dispatchers.Main) {
            article?.let { articleSafe ->
                newsRepository.delete(articleSafe)
            }
        }
    }
}