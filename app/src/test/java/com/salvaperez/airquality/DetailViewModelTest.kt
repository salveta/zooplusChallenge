package com.salvaperez.airquality

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.salvaperez.airquality.domain.model.ComponentNameModel
import com.salvaperez.airquality.presentation.detail.DetailViewModel
import com.salvaperez.airquality.presentation.utils.Resource
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.ArrayList


@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var vm: DetailViewModel

    @Before
    fun setUp() {
        vm = DetailViewModel()
    }

    @Test
    fun `onInit return an object if is not empty`() {
        vm.onInit(createComponentList())
        val resource = vm.airComponents.value
        assertEquals(vm.airComponents.value, Resource.success(createComponentList()))
        assertEquals(resource?.status, Resource.Status.SUCCESS)
    }

    @Test
    fun `onInit return error if  object is empty`() {
        vm.onInit(null)
        val resource = vm.airComponents.value
        assertEquals(resource?.status, Resource.Status.ERROR)
    }

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

}