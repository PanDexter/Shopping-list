package com.szeptun.shoppinglist

import com.szeptun.shoppinglist.domain.GetListsByState
import com.szeptun.shoppinglist.domain.SaveShoppingList
import com.szeptun.shoppinglist.entity.ListState
import com.szeptun.shoppinglist.entity.ShoppingList
import com.szeptun.shoppinglist.ui.Lists.ListViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Flowable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.joda.time.LocalDateTime
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ListViewModelTest {

    @MockK
    lateinit var getListsByState: GetListsByState
    @MockK
    lateinit var saveShoppingList: SaveShoppingList


    private fun createViewModel(listState: ListState): ListViewModel =
        ListViewModel(
            getListsByState = getListsByState,
            saveShoppingList = saveShoppingList,
            listState = listState
        )

    @Before
    fun setUp() {
        RxJavaPlugins.reset()
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.reset()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        MockKAnnotations.init(this)
    }

    @Test
    fun `get lists archive list state`() {
        //GIVEN
        val list = mockShoppingList(ListState.ARCHIVE)
        every { getListsByState.execute(any()) } returns Flowable.just(list)
        val viewModel = createViewModel(ListState.ARCHIVE)

        //WHEN
        val listObserver = viewModel.shoppingListItemsStream.test()

        //THEN
        listObserver.assertOf{
            Assert.assertEquals(listObserver.values()[0], list)
        }

    }

    @Test
    fun `get lists active list state`() {
        //GIVEN
        val list = mockShoppingList(ListState.ACTIVE)
        every { getListsByState.execute(any()) } returns Flowable.just(list)
        val viewModel = createViewModel(ListState.ACTIVE)

        //WHEN
        val listObserver = viewModel.shoppingListItemsStream.test()

        //THEN
        listObserver.assertOf{
            Assert.assertEquals(listObserver.values()[0], list)
        }
    }

    private fun mockShoppingList(listState: ListState): List<ShoppingList> = listOf(
        ShoppingList(
            id = 0,
            name = "",
            listState = listState,
            date = LocalDateTime.now(),
            itemsList = emptyList()
        ),
        ShoppingList(
            1,
            name = "",
            listState = listState,
            date = LocalDateTime.now(),
            itemsList = emptyList()
        )
    )

}