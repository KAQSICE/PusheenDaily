package com.tranced.pusheendailykt.main.view

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.tranced.pusheendailykt.R
import com.tranced.pusheendailykt.main.model.TopNewsItemsBean

/**
 * BannerAdapter
 * @author TranceD
 */
class BannerAdapter(topNewsItemsList: MutableList<TopNewsItemsBean.TopStoriesBean>) :
    PagerAdapter() {
    private val topNewsItemsList = topNewsItemsList

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return topNewsItemsList.size + 2
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = View.inflate(container.context, R.layout.main_news_list_banner_items, null)
        val topNewsTitle = view.findViewById<TextView>(R.id.top_news_title)
        val topNewsHint = view.findViewById<TextView>(R.id.top_news_hint)
        val topNewsImage = view.findViewById<ImageView>(R.id.top_news_image)
        when (position) {
            0 -> {
                Glide.with(view)
                    .load(topNewsItemsList[topNewsItemsList.size - 1].image)
                    .into(topNewsImage)
                topNewsTitle.text = topNewsItemsList[topNewsItemsList.size - 1].title
                topNewsHint.text = topNewsItemsList[topNewsItemsList.size - 1].hint
            }
            topNewsItemsList.size + 1 -> {
                Glide.with(view)
                    .load(topNewsItemsList[0].image)
                    .into(topNewsImage)
                topNewsTitle.text = topNewsItemsList[0].title
                topNewsHint.text = topNewsItemsList[0].hint
            }
            else -> {
                Glide.with(view)
                    .load(topNewsItemsList[position - 1].image)
                    .into(topNewsImage)
                topNewsTitle.text = topNewsItemsList[position - 1].title
                topNewsHint.text = topNewsItemsList[position - 1].hint
            }
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}