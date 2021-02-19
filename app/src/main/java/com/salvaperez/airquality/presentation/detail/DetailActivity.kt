package com.salvaperez.airquality.presentation.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.salvaperez.airquality.R
import com.salvaperez.airquality.domain.model.ComponentNameModel
import com.salvaperez.airquality.presentation.utils.Resource
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val vModel: DetailViewModel by currentScope.viewModel(this)
    private lateinit var detailAdapter: DetailAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        vModel.onInit(intent.getParcelableArrayListExtra<ComponentNameModel>(COMPONENTS_DATA))
        initViews()
        loadObservers()
    }

    private fun initViews() {
        detailAdapter = DetailAdapter()
        rvComponents.layoutManager = LinearLayoutManager(this)
        rvComponents.adapter = detailAdapter
    }

    private fun loadObservers(){
        vModel.airComponents.observe(this, Observer { place ->
            when(place.status){
                Resource.Status.SUCCESS -> showData(place.data as List<ComponentNameModel>)
            }
        })

        toolbarDetail.setOnClickListener { finish() }
    }

    private fun showData(data: List<ComponentNameModel>){
        detailAdapter.componentList = data
    }

    companion object{
        const val COMPONENTS_DATA = "components.data"
    }
}