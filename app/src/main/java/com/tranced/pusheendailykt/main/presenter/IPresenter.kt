package com.tranced.pusheendailykt.main.presenter

/**
 * IPresenter
 * @author TranceD
 */
interface IPresenter {
    /**
     * 获取标题栏天数
     * @return 返回天数
     */
    fun getDay(): String

    /**
     * 获取标题栏月份
     * @return 返回月份
     */
    fun getMonth(): String

    /**
     * 获取标题栏文字提示
     * @return 返回文字提示
     */
    fun getTitleText(): String

    /**
     * 获取新闻列表
     */
    fun getNewsItems()

    /**
     * 获取轮播图新闻
     */
    fun getBannerItems()

    /**
     * 获取轮播数据和新闻列表
     */
    fun getBothItems()

    /**
     * 获取url部分日期
     * @param countDownDays 倒数天数
     * @return 返回url后的日期
     */
    fun getDate(countDownDays: Int): String

    /**
     * 获取日期分割线用日期
     * @param countDownDays 倒数天数
     * @return 返回日期分割线用日期
     */
    fun getDateLineText(countDownDays: Int): String

    /**
     * 重置数据
     */
    fun resetData()
}