package br.com.multsoftware.v1.appnews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by João Bosco on 05/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
abstract class AbstractActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        onInject()
    }

    protected abstract fun getLayout() : Int

    protected abstract fun onInject()
}