package br.com.multsoftware.v1.appnews.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.multsoftware.v1.appnews.data.local.model.Article

/**
 * Created by João Bosco on 07/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAll(): LiveData<List<Article>>

    @Delete
    suspend fun delete(article: Article)
}