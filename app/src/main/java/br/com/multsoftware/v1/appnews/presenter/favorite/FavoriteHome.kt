package br.com.multsoftware.v1.appnews.presenter.favorite

import br.com.multsoftware.v1.appnews.data.local.model.Article

/**
 * Created by João Bosco on 06/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
interface FavoriteHome {

    interface Presenter {
        fun onSuccess(articles: List<Article>)
    }
}