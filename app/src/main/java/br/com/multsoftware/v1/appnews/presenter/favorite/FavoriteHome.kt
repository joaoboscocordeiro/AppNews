package br.com.multsoftware.v1.appnews.presenter.favorite

import br.com.multsoftware.v1.appnews.model.Article

/**
 * Created by João Bosco on 06/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
interface FavoriteHome {

    fun showArticles(articles: List<Article>)
}