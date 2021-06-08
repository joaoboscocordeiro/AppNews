package br.com.multsoftware.v1.appnews.view

import android.content.Intent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.multsoftware.v1.appnews.R
import br.com.multsoftware.v1.appnews.adapter.MainAdapter
import br.com.multsoftware.v1.appnews.model.Article
import br.com.multsoftware.v1.appnews.model.data.NewsDataSource
import br.com.multsoftware.v1.appnews.presenter.ViewHome
import br.com.multsoftware.v1.appnews.presenter.favorite.FavoritePresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AbstractActivity(), ViewHome.Favorite {

    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var presenter: FavoritePresenter

    override fun getLayout(): Int = R.layout.activity_favorite

    override fun onInject() {

        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this, dataSource)
        presenter.getAll()
        configRecycler()
        clickAdapter()

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
                presenter.deleteArticle(article)
                Snackbar.make(
                    viewHolder.itemView, R.string.article_delete_successful,
                    Snackbar.LENGTH_LONG
                ).apply {
                    setAction(getString(R.string.undo)) {
                        presenter.saveArticle(article)
                        mainAdapter.notifyDataSetChanged()
                    }
                    show()
                }
            }

        }

        ItemTouchHelper(itemTouchPerCallback).apply {
            attachToRecyclerView(rvFavorite)
        }

        presenter.getAll()
    }

    private fun configRecycler() {
        with(rvFavorite) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@FavoriteActivity, DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun clickAdapter() {
        mainAdapter.setOnClickListener { article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }

}
