package br.com.multsoftware.v1.appnews.ui.fragment.home

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
import br.com.multsoftware.v1.appnews.databinding.FragmentHomeBinding
import br.com.multsoftware.v1.appnews.repository.NewsRepository
import br.com.multsoftware.v1.appnews.ui.adapter.MainAdapter
import br.com.multsoftware.v1.appnews.ui.fragment.base.BaseFragment
import br.com.multsoftware.v1.appnews.util.StateResource

/**
 * Created by João Bosco on 16/05/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private val mainAdapter by lazy { MainAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observerResults()
    }

    private fun observerResults() {
        viewModel.getAll.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is StateResource.Success -> {
                    binding.rvProgressBar.visibility = View.INVISIBLE
                    response.data?.let { data ->
                        mainAdapter.differ.submitList(data.articles.toList())
                    }
                }
                is StateResource.Error -> {
                    binding.rvProgressBar.visibility = View.INVISIBLE
                    Toast.makeText(
                        requireContext(),
                        "Ocorreu um erro: ${response.message.toString()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is StateResource.Loading -> {
                    binding.rvProgressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvNews.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }

        mainAdapter.setOnClickListener { article ->
            val action = HomeFragmentDirections.actionHomeFragmentToWebViewFragment(article)
            findNavController().navigate(action)
        }
    }

    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getFragmentRepository(): NewsRepository =
        NewsRepository(RetrofitInstance.api, ArticleDatabase.invoke(requireContext()))

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)
}