# SSExpandableRecyclerView
### _Expandable Recyclerview make it easy to integrate nested recyclerview_ 

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)][git-repo-url] [![Kotlin Version](https://img.shields.io/badge/Kotlin-v1.5.10-blue.svg)](https://kotlinlang.org) [![Platform](https://img.shields.io/badge/Platform-Android-green.svg?style=flat)](https://www.android.com/) [![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

## Features

- Simple and easy to use ( no complex adapter required )
- Just extend BaseRecyclerAdapter and good to go.
- Get onClick event for parent recycler item as well child item.
- Customize where you want to collapse/expand effect.

# 🎬 Preview

| Expandable RecyclerView| Expandable RecyclerView| Expandable RecyclerView|
|--|--|--|
| ![me](https://github.com/SimformSolutionsPvtLtd/SSExpandableRecylerView/blob/main/Images/1st_sample.gif) | ![](https://github.com/SimformSolutionsPvtLtd/SSExpandableRecylerView/blob/main/Images/2nd_sample.gif) | ![](https://github.com/SimformSolutionsPvtLtd/SSExpandableRecylerView/blob/main/Images/3rd_sample.gif) |

## Installation
1. Extend BaseRecyclerAdapter in recycler adapter and implement below methods.
```kotlin
class ListAdapter : BaseRecyclerAdapter<ListDataModel, ChildDataModel>() {
// Pass parent and child model in BaseRecyclerAdapter

    override fun getLayoutIdForType(): Int = R.layout.item_parent  // Provide parent recycler item id

    override fun getLayoutIdForChild(): Int = R.layout.item_child  // Provide child recycler item id

    // Click event fot parent recycler item
    override fun onParentItemClick(triple: Triple<Int, Any, View>, viewDataBinding: ViewDataBinding) {
        val data = triple.second as ListDataModel // Here you can get parent item data for clicked item
        val position = triple.first // Get position of clicked item
        val view = triple.third // Get view of clicked item

        when (view.id) {
            R.id.text_movie_year -> {
                // Call this function where you want to expand collapse childView
                expandCollapse(triple.first, viewDataBinding)
            }
        }
    }

    // Click event for child rectycler item
    override fun onChildItemClicked(triple: Triple<Int, Any, View>, parentIndex: Int) {
        val data = triple.second as ChildDataModel // Here you can get child item data for clicked item
        val position = triple.first // Get position of clicked item
        val view = triple.third // Get view of clicked item

        when (view.id) {
            R.id.img_download_movie -> {
                // Here you can perform your action
            }
        }
    }
}
```
2. Create variables in parent recycler item as par below and bind it as per requirement.
```xml
    <data>
        <variable
            name="data" // Variable name should be same
            type="com.expandable.recyclerview.model.ListDataModel" />
        <variable
            name="clickHandler" // Variable name should be same
            type="android.view.View.OnClickListener" />
        <variable
            name="adapter" // Variable name should be same and give type  BaseChildRecyclerAdapter
            type="com.expandable.recyclerview.base.BaseChildRecyclerAdapter" />
        <variable
            name="isVisible" // Variable name should be same
            type="Boolean" />
        <import type="android.view.View" />
    </data>
```
3. SetUp child recyclerview with adapter and visiblty like this
```xml
    <androidx.recyclerview.widget.RecyclerView
                android:id="@+id/childRecycler"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="@dimen/_8sdp"
                android:visibility="@{isVisible ? View.VISIBLE : View.GONE }" // Important line
                app:setAdapter="@{adapter}" //Important line
                .... />
```
4. Create variables in child recycler item as par below and bind it as per requirement.
```xml
    <data>
        <variable
            name="data" // Variable name should be same
            type="com.expandable.recyclerview.model.ChildDataModel" />
        <variable
            name="clickHandler" // Variable name should be same
            type="android.view.View.OnClickListener" />
        <import type="android.view.View" />
    </data>
```
5. Assign click handler where you want to recive click event
```xml
  <TextView
        android:id="@+id/movieTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="@{clickHandler::onClick}" /> // Important line
```

## Find this sample useful? ❤️
Support it by joining __[stargazers]__ for this repository.⭐

## 🤝 How to Contribute

Whether you're helping us fix bugs, improve the docs, or a feature request, we'd love to have you! 💪
Check out our __[Contributing Guide]__ for ideas on contributing.

## Bugs and Feedback
For bugs, feature requests, and discussion please use __[GitHub Issues]__.

## Awesome Mobile Libraries
- Check out our other available [awesome mobile libraries](https://github.com/SimformSolutionsPvtLtd/Awesome-Mobile-Libraries)

## License
```
Copyright 2022 Simform Solutions

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and limitations under the License.
```

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
   [git-repo-url]: <https://github.com/SimformSolutionsPvtLtd/SSExpandableRecylerView.git>
   [stargazers]: <https://github.com/SimformSolutionsPvtLtd/SSExpandableRecylerView/stargazers>
   [Contributing Guide]: <https://github.com/SimformSolutionsPvtLtd/SSExpandableRecylerView/blob/main/CONTRIBUTING.md>
   [GitHub Issues]: <https://github.com/SimformSolutionsPvtLtd/SSExpandableRecylerView/issues>
