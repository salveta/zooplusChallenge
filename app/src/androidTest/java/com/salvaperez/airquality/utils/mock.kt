package com.salvaperez.airquality.utils

import com.salvaperez.airquality.domain.model.ComponentNameModel
import java.util.ArrayList

val mockedAirQuality = createComponentList()

private fun createComponentList(): ArrayList<ComponentNameModel> {
    val mutableList : MutableList<ComponentNameModel> = ArrayList()
    mutableList.add(ComponentNameModel("CO (Carbon monoxide)", "12.9898" + " ug/m3"))
    mutableList.add(ComponentNameModel("NO (Nitrogen monoxide)", "23.5678" + " ug/m3"))
    mutableList.add(ComponentNameModel("CO (Nitrogen dioxide)", "09.8978" + " ug/m3"))
    mutableList.add(ComponentNameModel("CO (Ozone)", "32.1234123432" + " ug/m3"))
    mutableList.add(ComponentNameModel("CO (Sulphur dioxide)", "155.2312312" + " ug/m3"))
    mutableList.add(ComponentNameModel("CO (Fine particles matter)", "18.768678" + " ug/m3"))
    mutableList.add(ComponentNameModel("CO (Coarse particulate matter)", "157.2343" + " ug/m3"))
    mutableList.add(ComponentNameModel("CO (Amnonia)", "34.8708" + " ug/m3"))

    return ArrayList(mutableList)
}