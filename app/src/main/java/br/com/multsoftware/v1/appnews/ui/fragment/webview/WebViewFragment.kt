package br.com.multsoftware.v1.appnews.ui.fragment.webview

import android.os.Bundle
import android.view.*
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import br.com.multsoftware.v1.appnews.R
import br.com.multsoftware.v1.appnews.data.local.db.ArticleDatabase
import br.com.multsoftware.v1.appnews.data.local.model.Article
import br.com.multsoftware.v1.appnews.data.remote.RetrofitInstance
import br.com.multsoftware.v1.appnews.databinding.FragmentWebViewBinding
import br.com.multsoftware.v1.appnews.repository.NewsRepository
import br.com.multsoftware.v1.appnews.ui.fragment.base.BaseFragment

/**
 * Created by João Bosco on 16/05/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class WebViewFragment : BaseFragment<WebViewViewModel, FragmentWebViewBinding>() {

    private val args: WebViewFragmentArgs by navArgs()
    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        article = args.article

        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { url ->
                loadUrl(url)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_article -> {
                viewModel.saveArticle(article)
                Toast.makeText(requireContext(), "Artigo Salvo com Sucesso.", Toast.LENGTH_LONG)
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getViewModel(): Class<WebViewViewModel> = WebViewViewModel::class.java

    override fun getFragmentRepository(): NewsRepository =
        NewsRepository(RetrofitInstance.api, ArticleDatabase.invoke(requireContext()))

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWebViewBinding = FragmentWebViewBinding.inflate(inflater, container, false)
}