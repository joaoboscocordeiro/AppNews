package br.com.multsoftware.v1.appnews.ui.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.multsoftware.v1.appnews.R
import br.com.multsoftware.v1.appnews.data.local.db.ArticleDatabase
import br.com.multsoftware.v1.appnews.data.remote.RetrofitInstance
import br.com.multsoftware.v1.appnews.databinding.FragmentFavoriteBinding
import br.com.multsoftware.v1.appnews.repository.NewsRepository
import br.com.multsoftware.v1.appnews.ui.adapter.MainAdapter
import br.com.multsoftware.v1.appnews.ui.fragment.base.BaseFragment
import br.com.multsoftware.v1.appnews.util.ArticleListEvent
import br.com.multsoftware.v1.appnews.util.ArticleListState
import com.google.android.material.snackbar.Snackbar

/**
 * Created by João Bosco on 16/05/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class FavoriteFragment : BaseFragment<FavoriteViewModel, FragmentFavoriteBinding>() {

    private val mainAdapter by lazy { MainAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dispatch(ArticleListEvent.Fetch)
        setupRecyclerView()
        observerResults()
    }

    private fun observerResults() {
        viewModel.favorite.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is ArticleListState.Success -> {
                    binding.txtEmptyList.visibility = View.INVISIBLE
                    mainAdapter.differ.submitList(response.list)
                }
                is ArticleListState.ErrorMessage -> {
                    binding.txtEmptyList.visibility = View.INVISIBLE
                    Toast.makeText(
                        requireContext(),
                        "Ocorreu um erro: ${response.errorMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is ArticleListState.Loading -> {
                    binding.txtEmptyList.visibility = View.VISIBLE
                }
                is ArticleListState.Empty -> {
                    binding.txtEmptyList.visibility = View.VISIBLE
                    mainAdapter.differ.submitList(emptyList())
                }
            }
        })
    }

    val itemTouchPerCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val article = mainAdapter.differ.currentList[position]
            viewModel.deleteArticle(article)
            Snackbar.make(
                viewHolder.itemView, R.string.article_delete_successful,
                Snackbar.LENGTH_LONG
            ).apply {
                setAction(getString(R.string.undo)) {
                    viewModel.saveArticle(article)
                    mainAdapter.notifyDataSetChanged()
                }
                show()
            }
            observerResults()
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvFavorite) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context, DividerItemDecoration.VERTICAL
                )
            )
            ItemTouchHelper(itemTouchPerCallback).apply {
                attachToRecyclerView(binding.rvFavorite)
            }
            mainAdapter.setOnClickListener { article ->
                val action =
                    FavoriteFragmentDirections.actionFavoriteFragmentToWebViewFragment(article)
                findNavController().navigate(action)
            }
        }
    }

    override fun getViewModel(): Class<FavoriteViewModel> = FavoriteViewModel::class.java

    override fun getFragmentRepository(): NewsRepository =
        NewsRepository(RetrofitInstance.api, ArticleDatabase.invoke(requireContext()))

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false)
}