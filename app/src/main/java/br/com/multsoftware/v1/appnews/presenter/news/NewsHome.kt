package br.com.multsoftware.v1.appnews.presenter.news

import br.com.multsoftware.v1.appnews.data.local.model.NewsResponse

/**
 * Created by João Bosco on 05/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
interface NewsHome {

    interface Presenter {

        fun requestAll()
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()

    }
}