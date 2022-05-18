package br.com.multsoftware.v1.appnews.util

import br.com.multsoftware.v1.appnews.data.local.model.Article

/**
 * Created by João Bosco on 16/05/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
sealed class ArticleListState {
    data class Success(val list: List<Article>) : ArticleListState()
    data class ErrorMessage(val errorMessage: String) : ArticleListState()
    object Loading : ArticleListState()
    object Empty : ArticleListState()
}
