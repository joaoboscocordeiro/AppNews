package br.com.multsoftware.v1.appnews.ui.fragment.webview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.multsoftware.v1.appnews.data.local.model.Article
import br.com.multsoftware.v1.appnews.repository.NewsRepository
import kotlinx.coroutines.launch

/**
 * Created by João Bosco on 16/05/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class WebViewViewModel constructor(
    private val repository: NewsRepository
): ViewModel() {

    fun saveArticle(article: Article) = viewModelScope.launch {
        repository.updateInsert(article)
    }
}