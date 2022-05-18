package br.com.multsoftware.v1.appnews.util

/**
 * Created by João Bosco on 16/05/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
sealed class ArticleListEvent {
    object Fetch : ArticleListEvent()
}
