package com.expandable.recyclerview.ui.activity

import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.expandable.recyclerview.R
import com.expandable.recyclerview.base.BaseCompactActivity
import com.expandable.recyclerview.databinding.ActivityMainBinding
import com.expandable.recyclerview.model.ChildDataModel
import com.expandable.recyclerview.model.ListDataModel
import com.expandable.recyclerview.ui.adapter.ListAdapter
import com.expandable.recyclerview.util.FIVE
import com.expandable.recyclerview.util.TWENTY
import com.expandable.recyclerview.util.TWO
import com.expandable.recyclerview.util.ZERO
import com.expandable.recyclerview.viewmodel.ActivityMainViewModel

class MainActivity : BaseCompactActivity<ActivityMainBinding, ActivityMainViewModel>(), View.OnClickListener {

    private val list = arrayListOf<ListDataModel>()
    private var childListArray = arrayListOf<ArrayList<ChildDataModel>>()
    private lateinit var listAdapter: ListAdapter

    override val viewModel: ActivityMainViewModel by viewModels()

    override fun getLayoutResID(): Int = R.layout.activity_main

    override fun initialize() {
        super.initialize()
        setDummyData()
        binding.apply {
            listAdapter = ListAdapter()
            listAdapter.apply {
                setList(list.toList(), childListArray.toList())
                listRecyclerView.adapter = this
            }
        }
    }

    //Setting dummy data
    private fun setDummyData() {
        for (i in ZERO..TWENTY) {
            if (i % TWO == ZERO) {
                ListDataModel().apply {
                    name = "Morning $i"
                    isExpanded = false
                    val childList: ArrayList<ChildDataModel> = arrayListOf()
                    for ( i in ZERO..FIVE) {
                        if (i % TWO == ZERO) {
                            val childData = ChildDataModel()
                            childData.name = "Child Morning $i"
                            childList.add(childData)
                        } else {
                            val childData = ChildDataModel()
                            childData.name = "Child Evening $i"
                            childList.add(childData)
                        }
                    }
                    childListArray.add(childList)
                    list.add(this)
                }
            } else {
                ListDataModel().apply {
                    name = "Evening $i"
                    isExpanded = false
                    val childList: ArrayList<ChildDataModel> = arrayListOf()
                    for (i in ZERO..FIVE) {
                        if (i % TWO == ZERO) {
                            val childData = ChildDataModel()
                            childData.name = "Child Morning $i"
                            childList.add(childData)
                        } else {
                            val childData = ChildDataModel()
                            childData.name = "Child Evening $i"
                            childList.add(childData)
                        }
                    }
                    childListArray.add(childList)
                    list.add(this)
                }
            }
        }
    }

    override fun initializeObserver(viewModel : ActivityMainViewModel) {
        super.initializeObserver(viewModel)
        listAdapter.liveOnClickData.observe(this, Observer {
            Toast.makeText(applicationContext,it,Toast.LENGTH_SHORT).show()
        })
    }
    override fun onClick(v : View?) {
        //Implement when need
    }
}