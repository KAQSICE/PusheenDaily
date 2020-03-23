package com.tranced.pusheendailykt.main.presenter

import com.tranced.pusheendailykt.commons.experimental.preference.hawk
import com.tranced.pusheendailykt.main.model.TopNewsItemsBean

object NewsPreference {
    var topNewsItems: MutableList<TopNewsItemsBean.TopStoriesBean> by hawk(
        "top_news_list",
        mutableListOf()
    )
}