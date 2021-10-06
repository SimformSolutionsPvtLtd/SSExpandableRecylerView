/*
* Copyright 2021 MyApp
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.expandable.recyclerview.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.expandable.recyclerview.BR

/**
 * Base recycler adapter for all recycler adapters.
 */
@Suppress("TooManyFunctions")
abstract class BaseRecyclerAdapter<ListModel, ChildItemModel>: RecyclerView.Adapter<BaseRecyclerAdapter<ListModel, ChildItemModel>.RecyclerHolder>(),
    BaseChildRecyclerAdapter.OnChildItemClickListener {

    protected val arrayList = ArrayList<ListModel>()
    private val childArrayList = ArrayList<List<ChildItemModel>>()
    protected var isExpandedArray = ArrayList<Boolean>()
    private lateinit var childRecyclerAdapter: BaseChildRecyclerAdapter<ChildItemModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getLayoutIdForParent(),
            parent,
            false
        )
        return RecyclerHolder(binding)
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.bind(arrayList[position], childArrayList[position], position)
    }

    /**
     * This is abstract function used to get view type for adapter
     */
    abstract fun getLayoutIdForParent(): Int

    abstract fun getLayoutIdForChild(): Int

    /**
     * This is abstract function used to get item click for all the adapter views
     */
    abstract fun onParentItemClick( triple: Triple<Int, Any, View>, viewDataBinding: ViewDataBinding )

    /**
     * This is inner class used to set recycler view holder.
     */
    inner class RecyclerHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root), View.OnClickListener {

        /**
         * This function is used to bind recycler data particular row wise.
         */
        fun bind(data: ListModel, childData: List<ChildItemModel>, positation: Int) {
            childRecyclerAdapter = BaseChildRecyclerAdapter(this@BaseRecyclerAdapter)
            childRecyclerAdapter.apply {
                setList(childData)
                setItemLayout(getLayoutIdForChild())
                setParentIndex(positation)
            }
            viewDataBinding.apply {
                setVariable(BR.data, data)
                setVariable(BR.clickHandler, this@RecyclerHolder)
                setVariable(BR.adapter, childRecyclerAdapter)
                setVariable(BR.isVisible, isExpandedArray[positation])
                executePendingBindings()
            }
        }

        //Parent item on click
        override fun onClick(v: View) {
            onParentItemClick(
                Triple(adapterPosition, arrayList[adapterPosition] as Any, v), viewDataBinding)
        }
    }

    /**
     * This fun is used to save list
     * @param newList ArrayList<ListModel>
     */
    fun setList(newList: List<ListModel>, childList: List<List<ChildItemModel>>) {
        arrayList.clear()
        arrayList.addAll(newList)
        childArrayList.clear()
        childArrayList.addAll(childList)
        isExpandedArray.clear()
        isExpandedArray.addAll(Array(arrayList.count()) { false })
        isListEmpty(arrayList.isEmpty())
    }

    open fun isListEmpty(isListEmpty: Boolean) {}

    fun getListItems(): ArrayList<ListModel> = arrayList

    //Expand collapse child recycler
    fun expandCollapse(adapterPosition: Int, viewDataBinding: ViewDataBinding) {
        if (isExpandedArray[adapterPosition]) {
            isExpandedArray[adapterPosition] = false
            viewDataBinding.setVariable(BR.isVisible, isExpandedArray[adapterPosition])
        } else {
            isExpandedArray[adapterPosition] = true
            viewDataBinding.setVariable(BR.isVisible, isExpandedArray[adapterPosition])
        }
    }
}

