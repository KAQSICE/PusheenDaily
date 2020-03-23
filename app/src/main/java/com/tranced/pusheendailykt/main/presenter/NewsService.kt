package com.tranced.pusheendailykt.main.presenter

import com.tranced.pusheendailykt.commons.experimental.cache.RefreshState
import com.tranced.pusheendailykt.commons.experimental.extensions.awaitAndHandle
import com.tranced.pusheendailykt.commons.experimental.network.ServiceFactory
import com.tranced.pusheendailykt.main.model.TopNewsItemsBean
import com.tranced.pusheendailykt.main.presenter.NewsPreference.topNewsItems
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET

interface NewsService {
    @GET("3/news/latest")
    fun loadBannerItems(): Deferred<TopNewsItemsBean>

    companion object : NewsService by ServiceFactory()
}

@JvmOverloads
fun loadBannerItems(callback: suspend (RefreshState<Unit>) -> Unit = {}) {
    GlobalScope.launch {
        NewsService.loadBannerItems().awaitAndHandle {
            callback(RefreshState.Failure(it))
        }?.let(fun(topNewsItemsBean: TopNewsItemsBean) {
            topNewsItems =
                topNewsItemsBean.top_stories as MutableList<TopNewsItemsBean.TopStoriesBean>
//            topNewsItemsTemp = topNewsItemsBean.top_stories as MutableList<TopNewsItemsBean.TopStoriesBean>
            callback(RefreshState.Success(Unit))
        })
    }
}

lateinit var topNewsItemsTemp: MutableList<TopNewsItemsBean.TopStoriesBean>