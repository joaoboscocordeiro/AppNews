package br.com.multsoftware.v1.appnews.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.multsoftware.v1.appnews.data.local.model.NewsResponse
import br.com.multsoftware.v1.appnews.repository.NewsRepository
import br.com.multsoftware.v1.appnews.util.StateResource
import kotlinx.coroutines.launch
import retrofit2.Response

/**
 * Created by João Bosco on 16/05/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class HomeViewModel constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _getAll = MutableLiveData<StateResource<NewsResponse>>()
    val getAll: LiveData<StateResource<NewsResponse>> get() = _getAll

    init {
        safeFetchAll()
    }

    private fun safeFetchAll() = viewModelScope.launch {
        _getAll.value = StateResource.Loading()
        try {
            val response = repository.getAllRemote()
            _getAll.value = handleResponse(response)
        } catch (e: Exception) {
            _getAll.value = StateResource.Error("Artigos não encontrados: ${e.message}")
        }
    }

    private fun handleResponse(response: Response<NewsResponse>): StateResource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                return StateResource.Success(values)
            }
        }
        return StateResource.Error(response.message())
    }

}