package br.com.multsoftware.v1.appnews.ui.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import br.com.multsoftware.v1.appnews.repository.NewsRepository

/**
 * Created by João Bosco on 16/05/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
abstract class BaseFragment<VM : ViewModel, VB : ViewBinding> : Fragment() {

    lateinit var binding: VB
    lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())

        viewModel = ViewModelProvider(viewModelStore, factory).get(getViewModel())
        return binding.root

    }

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentRepository(): NewsRepository

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): VB

}