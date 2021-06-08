package br.com.multsoftware.v1.appnews.presenter

import br.com.multsoftware.v1.appnews.model.Article

/**
 * Created by João Bosco on 05/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
interface ViewHome {

    interface View {

        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun showArticles(articles: List<Article>)

    }
    interface Favorite {

        fun showArticles(articles: List<Article>)
    }
}