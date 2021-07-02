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
class BaseChildRecyclerAdapter<ChildItemModel>(private val clickListener: OnChildItemClickListener) :
        RecyclerView.Adapter<BaseChildRecyclerAdapter<ChildItemModel>.RecyclerHolder>() {

    private val arrayList = ArrayList<ChildItemModel>()
    private var itemLayout = 0
    private var parentPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getLayoutIdForType(),
                parent,
                false
        )
        return RecyclerHolder(binding)
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    /**
     * This is abstract function used to get view type for adapter
     */
    private fun getLayoutIdForType(): Int {
        return itemLayout
    }

    /**
     * This is abstract function used to get item click for all the adapter views
     */
    fun onItemClick(view: View?, adapterPosition: Int) {
        view?.let { viewClicked ->
            clickListener.onChildItemClicked(Triple(adapterPosition, arrayList[adapterPosition] as Any, viewClicked), parentPosition)
        }
    }

    /**
     * This is inner class used to set recycler view holder.
     */
    inner class RecyclerHolder(private val viewDataBinding: ViewDataBinding) :
            RecyclerView.ViewHolder(viewDataBinding.root), View.OnClickListener {

        /**
         * This function is used to bind recycler data particular row wise.
         */
        fun bind(data: ChildItemModel) {
            viewDataBinding.apply {
                setVariable(BR.data, data)
                setVariable(BR.clickHandler, this@RecyclerHolder)
                executePendingBindings()
            }
        }

        //On child item click
        override fun onClick(v: View?) {
            onItemClick(v, adapterPosition)
        }
    }

    /**
     * This fun is used to save list
     * @param newList ArrayList<ChildItemModel>
     */
    fun setList(newList: List<ChildItemModel>) {
        arrayList.clear()
        arrayList.addAll(newList)
    }

    /**
     * This function used to set layout of child recycler view item
     */
    fun setItemLayout(item: Int) {
        itemLayout = item
    }

    fun setParentIndex(position: Int) {
        parentPosition = position
    }

    interface OnChildItemClickListener {
        fun onChildItemClicked(triple: Triple<Int, Any, View>, parentIndex: Int)
    }
}

