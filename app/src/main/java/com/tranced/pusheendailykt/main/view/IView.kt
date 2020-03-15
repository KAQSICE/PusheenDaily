package com.tranced.pusheendailykt.main.view

/**
 * IView
 * @author TranceD
 */
interface IView {
    /**
     * 获取标题栏天数
     * @return 返回标题栏天数
     */
    fun getDay(): String

    /**
     * 获取标题栏月份
     * @return 返回标题栏月份
     */
    fun getMonth(): String

    /**
     * 获取标题栏文字提示
     * @return 返回温馨提示（？
     */
    fun getTitleText(): String

    /**
     * 设置标题栏
     */
    fun initToolbar()

    /**
     * 初始化新闻列表
     */
    fun initNewsList()

    /**
     * 下拉刷新
     */
    fun initSwipeRefreshLayout()

    /**
     * 获取新闻列表
     */
    fun getNewsItems()

    /**
     * 获取轮播数据和新闻列表
     */
    fun getBothItems()

    /**
     * 重置数据
     */
    fun resetData()
}