package com.expandable.recyclerview.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.expandable.recyclerview.R
import com.expandable.recyclerview.base.BaseCompactActivity
import com.expandable.recyclerview.databinding.ActivityMainBinding
import com.expandable.recyclerview.model.ChildDataModel
import com.expandable.recyclerview.model.ListDataModel
import com.expandable.recyclerview.ui.adapter.ListAdapter
import com.expandable.recyclerview.util.DIRECTOR_NAME
import com.expandable.recyclerview.util.GENRE
import com.expandable.recyclerview.util.LOADING_DELAY
import com.expandable.recyclerview.util.MOVIE_TITLE
import com.expandable.recyclerview.util.SIX
import com.expandable.recyclerview.util.TWENTY
import com.expandable.recyclerview.util.ZERO
import com.expandable.recyclerview.viewmodel.ActivityMainViewModel

class MainActivity : BaseCompactActivity<ActivityMainBinding, ActivityMainViewModel>(),
        View.OnClickListener {

    private val list = arrayListOf<ListDataModel>()
    private var childListArray = arrayListOf<ArrayList<ChildDataModel>>()
    private lateinit var listAdapter: ListAdapter

    override val viewModel: ActivityMainViewModel by viewModels()

    override fun getLayoutResID(): Int = R.layout.activity_main

    override fun initialize() {
        super.initialize()
        binding.apply {
            listAdapter = ListAdapter()
            listAdapter.apply {
                listRecyclerView.adapter = this
            }
            progressBar.isVisible = true
            textEmptyPlaceholder.isVisible = false
            imageEmptyPlaceholder.isVisible = true
            listRecyclerView.isVisible = false
        }

        Handler(Looper.getMainLooper()).postDelayed({
            setDummyData()
            listAdapter.apply {
                setList(list.toList(), childListArray.toList())
                notifyDataSetChanged()
            }
        }, LOADING_DELAY)
    }

    override fun initializeObserver(viewModel: ActivityMainViewModel) {
        super.initializeObserver(viewModel)
        listAdapter.liveOnClickData.observe(this, {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })

        listAdapter.clickOnShare.observe(this, {
            Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                startActivity(Intent.createChooser(this, getString(R.string.share_to)))
            }
        })

        listAdapter.isListEmptyLiveData.observe(this, { isEmpty ->
            if (isEmpty) {
                binding.apply {
                    progressBar.isVisible = false
                    textEmptyPlaceholder.isVisible = true
                    imageEmptyPlaceholder.isVisible = true
                    listRecyclerView.isVisible = false
                }
            } else {
                binding.apply {
                    progressBar.isVisible = false
                    textEmptyPlaceholder.isVisible = false
                    imageEmptyPlaceholder.isVisible = false
                    listRecyclerView.isVisible = true
                }
            }
        })
    }

    override fun onClick(v: View?) {
        //Implement when need
    }

    //Setup dummy data
    @SuppressLint("StringFormatMatches")
    private fun setDummyData() {
        for (i in ZERO..TWENTY) {
            ListDataModel().apply {
                val releaseYear = i + 2000
                movieYear = getString(R.string.movie_released, releaseYear)
                isExpanded = false
                val childList: ArrayList<ChildDataModel> = arrayListOf()
                for (item in ZERO..SIX) {
                    val childData = ChildDataModel()
                    childData.movieTitle = MOVIE_TITLE[item]
                    childData.director = DIRECTOR_NAME[item]
                    childData.genre = GENRE[item]
                    childList.add(childData)
                }
                childListArray.add(childList)
                list.add(this)
            }
        }
    }
}