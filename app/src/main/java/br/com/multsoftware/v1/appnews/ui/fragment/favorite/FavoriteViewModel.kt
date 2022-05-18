package br.com.multsoftware.v1.appnews.ui.fragment.favorite

import androidx.lifecycle.*
import androidx.lifecycle.switchMap
import br.com.multsoftware.v1.appnews.data.local.model.Article
import br.com.multsoftware.v1.appnews.repository.NewsRepository
import br.com.multsoftware.v1.appnews.util.ArticleListEvent
import br.com.multsoftware.v1.appnews.util.ArticleListState
import kotlinx.coroutines.launch

/**
 * Created by João Bosco on 16/05/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class FavoriteViewModel constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _favorite = MutableLiveData<ArticleListEvent>()

    val favorite: LiveData<ArticleListState> = _favorite.switchMap {
        when (it) {
            ArticleListEvent.Fetch -> getAll()
        }
    }

    fun dispatch(event: ArticleListEvent.Fetch) {
        this._favorite.postValue(event)
    }

    private fun getAll(): LiveData<ArticleListState> {
        return liveData {
            try {
                emit(ArticleListState.Loading)
                val listLiveData = repository.getAll()
                    .map {list ->
                        if (list.isEmpty()) {
                            ArticleListState.Empty
                        } else {
                            ArticleListState.Success(list)
                        }
                    }
                emitSource(listLiveData)
            } catch (e: Exception) {
                emit(ArticleListState.ErrorMessage("Algo deu errado: ${e.message}"))
            }
        }
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        repository.updateInsert(article)
    }

    fun deleteArticle(article: Article) = viewModelScope.launch {
        repository.delete(article)
    }

}