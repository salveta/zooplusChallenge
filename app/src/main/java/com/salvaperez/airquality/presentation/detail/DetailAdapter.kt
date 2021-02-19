package com.salvaperez.airquality.presentation.detail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.salvaperez.airquality.R
import com.salvaperez.airquality.domain.model.ComponentNameModel
import com.salvaperez.airquality.presentation.extensions.basicDiffUtil
import com.salvaperez.airquality.presentation.extensions.inflate
import kotlinx.android.synthetic.main.item_air_quality.view.*

class DetailAdapter: RecyclerView.Adapter<DetailAdapter.ViewHolder>(){

    var componentList: List<ComponentNameModel> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new ->
            old.name == new.name }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_air_quality, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return componentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoModel = componentList[position]
        holder.bind(videoModel)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(component: ComponentNameModel) {
            itemView.txDate.text = component.name
            itemView.txAirQuality.text = component.value
        }
    }
}