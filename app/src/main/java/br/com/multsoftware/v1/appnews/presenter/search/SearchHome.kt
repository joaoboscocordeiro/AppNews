package br.com.multsoftware.v1.appnews.presenter.search

import br.com.multsoftware.v1.appnews.model.NewsResponse

/**
 * Created by João Bosco on 05/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
interface SearchHome {

    interface Presenter {

        fun search(term: String)
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()

    }
}