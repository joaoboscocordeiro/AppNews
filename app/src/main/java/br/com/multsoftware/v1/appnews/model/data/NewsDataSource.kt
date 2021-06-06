package br.com.multsoftware.v1.appnews.model.data

import br.com.multsoftware.v1.appnews.network.RetrofitInstance
import br.com.multsoftware.v1.appnews.presenter.news.NewsHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by João Bosco on 05/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
class NewsDataSource {

    fun getBreakingNews(callback: NewsHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getBreakingNews("br")
            if (response.isSuccessful) {
                response.body()?.let {newsResponse ->
                    callback.onSuccess(newsResponse)
                }
                callback.onComplete()
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }
}