package com.tranced.pusheendailykt.main.presenter

import android.util.Log
import com.google.gson.Gson
import com.tranced.pusheendailykt.Constants
import com.tranced.pusheendailykt.main.model.NewsItemsBean
import com.tranced.pusheendailykt.main.model.TopNewsItemsBean
import okhttp3.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Presenter: IPresenter {
    private var countDownDays: Int = Constants.COUNT_DOWN_DAYS_INITIAL
    var newsItems: MutableList<NewsItemsBean.StoriesBean>? = null
    var topNewsItems: MutableList<TopNewsItemsBean.TopStoriesBean>? = null

    override fun getDay(): String {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
    }

    override fun getMonth(): String {
        when (Calendar.getInstance().get(Calendar.MONTH) + 1) {
            Constants.JAN_NUM -> return Constants.JAN
            Constants.FEB_NUM -> return Constants.FEB
            Constants.MAR_NUM -> return Constants.MAR
            Constants.APR_NUM -> return Constants.APR
            Constants.MAY_NUM -> return Constants.MAY
            Constants.JUN_NUM -> return Constants.JUN
            Constants.JUL_NUM -> return Constants.JUL
            Constants.AUG_NUM -> return Constants.AUG
            Constants.SEP_NUM -> return Constants.SEP
            Constants.OCT_NUM -> return Constants.OCT
            Constants.NOV_NUM -> return Constants.NOV
            Constants.DEC_NUM -> return Constants.DEC
            else -> return Constants.JAN
        }
    }

    override fun getTitleText(): String {
        val hour = Calendar.getInstance()[Calendar.HOUR_OF_DAY]
        return if (hour >= Constants.MORNING && hour < Constants.NOON) {
            Constants.MORNING_TEXT
        } else if (hour >= Constants.NOON && hour < Constants.AFTERNOON) {
            Constants.NOON_TEXT
        } else if (hour >= Constants.AFTERNOON && hour < Constants.EVENING) {
            Constants.AFTERNOON_TEXT
        } else if (hour >= Constants.EVENING && hour < Constants.NIGHT) {
            Constants.EVENING_TEXT
        } else {
            Constants.NIGHT_TEXT
        }
    }

    override fun getNewsItems() {
        if (countDownDays > Constants.COUNT_DOWN_DAYS_INITIAL) {
            newsItems?.add(NewsItemsBean.StoriesBean(Constants.DATELINE_SYMBOL,
                getDateLineText(countDownDays + 1)))
        }
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://news-at.zhihu.com/api/3/news/before/" + getDate(countDownDays))
            .get()
            .build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("网络请求", "获取新闻列表失败")
            }

            override fun onResponse(call: Call, response: Response) {
                val gson = Gson()
                val newsItemsBean: NewsItemsBean = gson
                    .fromJson(response.body?.string(), NewsItemsBean::class.java)
                if (newsItems.isNullOrEmpty()) {
                    newsItems = newsItemsBean.stories?.toMutableList()
                } else {
                    newsItems!!.addAll(newsItemsBean.stories?.toMutableList()!!)
                }
            }
        })
        countDownDays ++
    }

    override fun getBannerItems() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://news-at.zhihu.com/api/3/news/latest")
            .get()
            .build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("网络请求", "Banner请求失败")
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val gson = Gson()
                val topNewsItemsBean = gson
                    .fromJson(response.body!!.string(), TopNewsItemsBean::class.java)
                topNewsItems = topNewsItemsBean.top_stories?.toMutableList()
            }
        })
    }

    override fun getDate(countDownDays: Int): String {
        return SimpleDateFormat("yyyyMMdd")
            .format(Date(System.currentTimeMillis()
                    - Constants.MILLISECONDS_OF_A_DAY * (countDownDays - 1)))
    }

    override fun getDateLineText(countDownDays: Int): String {
        return SimpleDateFormat("yyyy年MM月dd日")
            .format(Date(System.currentTimeMillis()
                    - Constants.MILLISECONDS_OF_A_DAY * (countDownDays - 1)))
    }

    override fun resetData() {
        newsItems = null
        countDownDays = Constants.COUNT_DOWN_DAYS_INITIAL
    }
}