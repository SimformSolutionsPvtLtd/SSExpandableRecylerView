package com.expandable.recyclerview.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.expandable.recyclerview.BR

abstract class BaseCompactActivity<Binding : ViewDataBinding, ViewModel : androidx.lifecycle.ViewModel> : AppCompatActivity() {
    protected lateinit var binding: Binding
    protected abstract val viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViewModel()
        initializeObserver(viewModel)
    }

    abstract fun getLayoutResID(): Int

    private fun bindViewModel() {
        binding = DataBindingUtil.setContentView(this, getLayoutResID())
        binding.apply {
            lifecycleOwner = this@BaseCompactActivity
            setVariable(BR.viewModel, viewModel)
        }
        initialize()
    }

    open fun initializeObserver(viewModel: ViewModel) {}

    open fun initialize() {}
}