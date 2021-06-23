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
package com.expandable.recyclerview.ui.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import com.expandable.recyclerview.R
import com.expandable.recyclerview.base.BaseRecyclerAdapter
import com.expandable.recyclerview.model.ChildDataModel
import com.expandable.recyclerview.model.ListDataModel

class ListAdapter : BaseRecyclerAdapter<ListDataModel, ChildDataModel>() {

    val liveOnClickData = MutableLiveData<String>()

    override fun getLayoutIdForType(): Int = R.layout.item_user

    override fun getLayoutIdForChild(): Int = R.layout.item_child

    override fun onParentItemClick(triple : Triple<Int , Any , View>, viewDataBinding : ViewDataBinding) {
        val data = triple.second as ListDataModel
        liveOnClickData.value = data.name
        when(triple.third.id) {
            R.id.name -> {
                //Call this function where you want to expand collapse childView
                expandCollapse(triple.first,viewDataBinding)
            }
        }
    }

    override fun onChildItemClicked(triple : Triple<Int , Any , View>, parentIndex: Int) {
        val data = triple.second as ChildDataModel
        liveOnClickData.value = data.name+" From "+ arrayList[parentIndex].name
    }
}


