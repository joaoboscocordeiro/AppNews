package br.com.multsoftware.v1.appnews.ui.activity

import androidx.appcompat.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {

//    private val mainAdapter by lazy {
//        MainAdapter()
//    }
//
//    private lateinit var presenter: SearchPresenter
//    private lateinit var binding: ActivitySearchBinding
//
//    override fun getLayout(): ViewBinding {
//        binding = ActivitySearchBinding.inflate(layoutInflater)
//        return binding
//    }
//
//    override fun onInject() {
//
//        val dataSource = NewsDataSource(this)
//        presenter = SearchPresenter(this, dataSource)
//        configRecycler()
//        search()
//        clickAdapter()
//    }
//
//    private fun search() {
//        searchNews.setOnQueryTextListener(
//            UtilQueryTextListener(
//                this.lifecycle
//            ) { newText ->
//                newText?.let { query ->
//                    if (query.isNotEmpty()) {
//                        presenter.search(query)
//                        rvProgressBarSearch.visibility = View.VISIBLE
//                    }
//                }
//            }
//        )
//    }
//
//    private fun configRecycler() {
//        with(rvSearch) {
//            adapter = mainAdapter
//            layoutManager = LinearLayoutManager(this@SearchActivity)
//            addItemDecoration(
//                DividerItemDecoration(
//                    this@SearchActivity, DividerItemDecoration.VERTICAL
//                )
//            )
//        }
//    }
//
//    private fun clickAdapter() {
//        mainAdapter.setOnClickListener { article ->
//            val intent = Intent(this, ArticleActivity::class.java)
//            intent.putExtra("article", article)
//            startActivity(intent)
//        }
//    }
//
//    override fun showProgressBar() {
//        rvProgressBarSearch.visibility = View.VISIBLE
//    }
//
//    override fun showFailure(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
//    }
//
//    override fun hideProgressBar() {
//        rvProgressBarSearch.visibility = View.INVISIBLE
//    }
//
//    override fun showArticles(articles: List<Article>) {
//        mainAdapter.differ.submitList(articles.toList())
//    }
}
