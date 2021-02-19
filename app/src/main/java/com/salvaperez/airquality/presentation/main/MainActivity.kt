package com.salvaperez.airquality.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.espresso.idling.CountingIdlingResource
import com.salvaperez.airquality.R
import com.salvaperez.airquality.domain.model.AirDetailsModel
import com.salvaperez.airquality.domain.model.ComponentNameModel
import com.salvaperez.airquality.presentation.detail.DetailActivity
import com.salvaperez.airquality.presentation.extensions.invisible
import com.salvaperez.airquality.presentation.extensions.open
import com.salvaperez.airquality.presentation.extensions.visible
import com.salvaperez.airquality.presentation.utils.Resource
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    var idlingResource = CountingIdlingResource("AirLoader")

    private val vModel: MainViewModel by currentScope.viewModel(this)
    private lateinit var adapterMain: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vModel.onInit()
        initViews()
        loadObservers()
    }

    private fun initViews() {
        idlingResource.increment()
        adapterMain = MainAdapter(this@MainActivity){
            onclick(it)
        }

        rvAirQuality.layoutManager = LinearLayoutManager(this)
        rvAirQuality.adapter = adapterMain
    }

    private fun loadObservers() {
        vModel.airData.observe(this, Observer { products ->
            when(products.status){
                Resource.Status.SUCCESS -> showAirQuality(products.data as List<AirDetailsModel>)
                Resource.Status.ERROR -> showError()
                Resource.Status.LOADING -> loading(products.data as Boolean)
            }
        })
    }

    private fun showAirQuality(data: List<AirDetailsModel>){
        adapterMain.dataList = data
        idlingResource.decrement()
    }

    private fun loading(isLoading: Boolean) {
        if(isLoading) {
            progressBar.visible()
        } else {
            progressBar.invisible()
        }
    }

    private fun showError(){
        Toast.makeText(this@MainActivity, getString(R.string.no_data), Toast.LENGTH_SHORT).show()
    }

    private fun onclick(data: List<ComponentNameModel>){
        val extras = Bundle()
        extras.putParcelableArrayList(DetailActivity.COMPONENTS_DATA, ArrayList(data))
        open(DetailActivity::class.java, extras)
    }
}