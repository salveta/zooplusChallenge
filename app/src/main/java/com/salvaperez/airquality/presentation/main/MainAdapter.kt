package com.salvaperez.airquality.presentation.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.salvaperez.airquality.R
import com.salvaperez.airquality.domain.model.AirDetailsModel
import com.salvaperez.airquality.domain.model.ComponentNameModel
import com.salvaperez.airquality.presentation.extensions.basicDiffUtil
import kotlinx.android.synthetic.main.item_air_quality.view.*
import kotlinx.android.synthetic.main.item_top.view.*


class MainAdapter(private val context: Context, private val onclick: (List<ComponentNameModel>) -> Unit): RecyclerView.Adapter<MainAdapter.BaseViewHolder<*>>(){

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }

    var dataList: List<AirDetailsModel> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new ->
            old.dataTime == new.dataTime }
    )

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0){
            TYPE_TOP
        } else {
            TYPE_NORMAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_TOP -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_top, parent, false)
                TopViewHolder(view)
            }
            TYPE_NORMAL -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_air_quality, parent, false)
                NormalViewHolder(view, onclick)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = dataList[position]
        when (holder) {
            is TopViewHolder -> holder.bind(element)
            is NormalViewHolder -> holder.bind(element)
            else -> throw IllegalArgumentException()
        }
    }

    inner class TopViewHolder(itemView: View) : BaseViewHolder<AirDetailsModel>(itemView) {
        override fun bind(item: AirDetailsModel) {
            itemView.txTop.text = item.airQualityName
            itemView.txDateTop.text = item.airQualityDate
        }
    }

    inner class NormalViewHolder(itemView: View, val onClickProduct: (List<ComponentNameModel>) -> Unit) : BaseViewHolder<AirDetailsModel>(itemView) {
        override fun bind(airData: AirDetailsModel) {
            itemView.txDate.text = airData.dataTime
            itemView.txAirQuality.text = airData.airQualityName

            if(airData.airQualityId == 1 || airData.airQualityId == 2){
                itemView.constAirQuality.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.green))
            }else if(airData.airQualityId == 3){
                itemView.constAirQuality.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.yellow))
            }else {
                itemView.constAirQuality.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.red))
            }

            itemView.constAirQuality.setOnClickListener {
                onClickProduct(airData.components)
            }
        }
    }

    companion object {
        private const val TYPE_TOP = 0
        private const val TYPE_NORMAL = 1
    }
}