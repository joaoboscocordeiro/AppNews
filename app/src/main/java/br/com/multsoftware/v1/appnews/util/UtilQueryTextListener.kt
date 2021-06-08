package br.com.multsoftware.v1.appnews.util

import android.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.coroutineScope
import br.com.multsoftware.v1.appnews.util.Constants.Companion.SEARCH_NEWS_DELAY
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by João Bosco on 07/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
class UtilQueryTextListener(
    lifecycle: Lifecycle,
    private val utilQueryTextListener: (String?) -> Unit
) : SearchView.OnQueryTextListener, LifecycleObserver {

    private val coroutineScopo = lifecycle.coroutineScope
    private var searchJob: Job? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScopo.launch {
            newText?.let {
                delay(SEARCH_NEWS_DELAY)
                utilQueryTextListener(newText)
            }
        }
        return false
    }
}