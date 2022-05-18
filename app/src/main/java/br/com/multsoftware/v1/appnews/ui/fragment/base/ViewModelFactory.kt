package br.com.multsoftware.v1.appnews.ui.fragment.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.multsoftware.v1.appnews.repository.NewsRepository
import br.com.multsoftware.v1.appnews.ui.fragment.favorite.FavoriteViewModel
import br.com.multsoftware.v1.appnews.ui.fragment.home.HomeViewModel
import br.com.multsoftware.v1.appnews.ui.fragment.search.SearchViewModel
import br.com.multsoftware.v1.appnews.ui.fragment.webview.WebViewViewModel

/**
 * Created by João Bosco on 16/05/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class ViewModelFactory(
    private val repository: NewsRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(repository) as T
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(repository) as T
            modelClass.isAssignableFrom(WebViewViewModel::class.java) -> WebViewViewModel(repository) as T
            else -> throw IllegalArgumentException("ViewModel não encontrado.")
        }
    }

}