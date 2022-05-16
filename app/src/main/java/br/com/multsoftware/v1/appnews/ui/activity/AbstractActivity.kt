package br.com.multsoftware.v1.appnews.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Created by João Bosco on 05/06/2021.
 * e-mail - Support: ti.junior@gmail.com
 */
abstract class AbstractActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout().root)
        onInject()
    }

    protected abstract fun getLayout() : ViewBinding

    protected abstract fun onInject()
}