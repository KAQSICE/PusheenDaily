package com.tranced.pusheendailykt.main.view

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * OnLoadingListener
 * @author TranceD
 */
abstract class OnLoadingListener : RecyclerView.OnScrollListener() {
    private var isScrollingUp = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        isScrollingUp = dy > 0
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        val linearLayoutManager: LinearLayoutManager =
            recyclerView.layoutManager as LinearLayoutManager
        when (newState) {
            RecyclerView.SCROLL_STATE_IDLE -> {
                val lastItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()
                val itemCount = linearLayoutManager.itemCount
                if (lastItemPosition >= itemCount - 1) {
                    onLoading()
                }
            }
        }
    }

    abstract fun onLoading()
}