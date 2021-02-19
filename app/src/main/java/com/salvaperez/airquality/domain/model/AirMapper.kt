package com.salvaperez.airquality.domain.model

import com.salvaperez.airquality.data.entity.AirDetailsEntity
import com.salvaperez.airquality.data.entity.ComponentsEntity
import com.salvaperez.airquality.presentation.extensions.ParseMapperHelper
import java.util.*

fun AirDetailsEntity.toModel(): AirDetailsModel{
    return AirDetailsModel(
        components = createComponentList(components.toModel()),
        airQualityId = main.aqi,
        airQualityName = ParseMapperHelper().parseAirQuality(main.aqi),
        dataTime = ParseMapperHelper().getHourDateTime(dt.toString()),
        airQualityDate = ParseMapperHelper().getDateTime(dt.toString())
    )
}

fun ComponentsEntity.toModel(): ComponentsModel {
    return ComponentsModel(
        co = co,
        nh3 = nh3,
        no = no,
        no2 = no2,
        o3 = o3,
        pm10 = pm10,
        pm2_5 = pm2_5,
        so2 = so2
    )
}

private fun createComponentList(component: ComponentsModel): List<ComponentNameModel> {
    val mutableList : MutableList<ComponentNameModel> = ArrayList()
    mutableList.add(ComponentNameModel("CO (Carbon monoxide)", ParseMapperHelper().parseDecimal(component.co) + " ug/m3"))
    mutableList.add(ComponentNameModel("NO (Nitrogen monoxide)", ParseMapperHelper().parseDecimal(component.no) + " ug/m3"))
    mutableList.add(ComponentNameModel("CO (Nitrogen dioxide)", ParseMapperHelper().parseDecimal(component.no2) + " ug/m3"))
    mutableList.add(ComponentNameModel("CO (Ozone)", ParseMapperHelper().parseDecimal(component.o3) + " ug/m3"))
    mutableList.add(ComponentNameModel("CO (Sulphur dioxide)", ParseMapperHelper().parseDecimal(component.so2) + " ug/m3"))
    mutableList.add(ComponentNameModel("CO (Fine particles matter)", ParseMapperHelper().parseDecimal(component.pm2_5) + " ug/m3"))
    mutableList.add(ComponentNameModel("CO (Coarse particulate matter)", ParseMapperHelper().parseDecimal(component.pm10) + " ug/m3"))
    mutableList.add(ComponentNameModel("CO (Amnonia)", ParseMapperHelper().parseDecimal(component.nh3) + " ug/m3"))

    return mutableList.toList()
}