package br.com.multsoftware.v1.appnews.ui.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.multsoftware.v1.appnews.data.local.db.ArticleDatabase
import br.com.multsoftware.v1.appnews.data.remote.RetrofitInstance
import br.com.multsoftware.v1.appnews.databinding.FragmentSearchBinding
import br.com.multsoftware.v1.appnews.repository.NewsRepository
import br.com.multsoftware.v1.appnews.ui.adapter.MainAdapter
import br.com.multsoftware.v1.appnews.ui.fragment.base.BaseFragment
import br.com.multsoftware.v1.appnews.util.StateResource
import br.com.multsoftware.v1.appnews.util.UtilQueryTextListener

/**
 * Created by João Bosco on 16/05/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    private val mainAdapter by lazy { MainAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        search()
        observerResults()
    }

    private fun observerResults() {
        viewModel.search.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is StateResource.Success -> {
                    binding.rvProgressBarSearch.visibility = View.INVISIBLE
                    response.data?.let { data ->
                        mainAdapter.differ.submitList(data.articles.toList())
                    }
                }
                is StateResource.Error -> {
                    binding.rvProgressBarSearch.visibility = View.INVISIBLE
                    Toast.makeText(
                        requireContext(),
                        "Ocorreu um erro: ${response.message.toString()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is StateResource.Loading -> {
                    binding.rvProgressBarSearch.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun search() {
        binding.searchNews.setOnQueryTextListener(
            UtilQueryTextListener(
                this.lifecycle
            ) { newText ->
                newText?.let { query ->
                    if (query.isNotEmpty()) {
                        viewModel.fetchSearch(query)
                        binding.rvProgressBarSearch.visibility = View.VISIBLE
                    }
                }
            }
        )
    }

    private fun setupRecyclerView() {
        binding.rvSearch.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }

        mainAdapter.setOnClickListener { article ->
            val action = SearchFragmentDirections.actionSearchFragmentToWebViewFragment(article)
            findNavController().navigate(action)
        }
    }

    override fun getViewModel(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun getFragmentRepository(): NewsRepository =
        NewsRepository(RetrofitInstance.api, ArticleDatabase.invoke(requireContext()))

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)
}