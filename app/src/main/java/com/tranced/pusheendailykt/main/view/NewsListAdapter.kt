package com.tranced.pusheendailykt.main.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.tranced.pusheendailykt.Constants
import com.tranced.pusheendailykt.R
import com.tranced.pusheendailykt.detail.DetailActivity
import com.tranced.pusheendailykt.main.model.NewsItemsBean
import com.tranced.pusheendailykt.main.model.TopNewsItemsBean
import java.util.*

/**
 * NewsListAdapter
 * @author TranceD
 */
class NewsListAdapter(
    mContext: Context,
    newsItemsList: MutableList<NewsItemsBean.StoriesBean>?,
    topNewsItemsList: MutableList<TopNewsItemsBean.TopStoriesBean>?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var newsItems: MutableList<NewsItemsBean.StoriesBean> = newsItemsList!!
    private var topNewsItems: MutableList<TopNewsItemsBean.TopStoriesBean> = topNewsItemsList!!
    private var mLayoutInflater = LayoutInflater.from(mContext)
    private var isBannerScroll: Boolean = false
    private lateinit var handler: MyHandler
    private lateinit var mTimer: Timer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mView: View
        when (viewType) {
            Constants.BANNER_TYPE -> {
                mView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.main_news_list_banner, parent, false)
                return BannerViewHolder(
                    mView
                )
            }
            Constants.NEWS_ITEM_TYPE -> {
                mView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.main_news_list_news_items, parent, false)
                return NewsItemsViewHolder(
                    mView
                )
            }
            Constants.DATELINE_TYPE -> {
                mView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.main_news_list_dateline, parent, false)
                return DatelineViewHolder(
                    mView
                )
            }
            else -> {
                mView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.main_news_list_loading, parent, false)
                return LoadingViewHolder(
                    mView
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return newsItems.size + 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            Constants.FIRST_ITEM_POSITION -> Constants.BANNER_TYPE
            newsItems.size + 1 -> Constants.LOADING_TYPE
            else -> {
                when (newsItems[position - 1].type) {
                    Constants.DATELINE_SYMBOL -> Constants.DATELINE_TYPE
                    else -> Constants.NEWS_ITEM_TYPE
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerViewHolder -> {
                val viewHolder: BannerViewHolder = holder
                viewHolder.banner.adapter =
                    BannerAdapter(
                        topNewsItems
                    )
                viewHolder.banner.currentItem = 1
                setAutoPlay(viewHolder)
                setBannerListeners(viewHolder)
            }

            is NewsItemsViewHolder -> {
                val viewHolder: NewsItemsViewHolder = holder
                viewHolder.newsTitle.text = newsItems[position - 1].title
                viewHolder.newsHint.text = newsItems[position - 1].hint
                Glide.with(mLayoutInflater.context)
                    .load(newsItems[position - 1].images!![0])
                    .into(viewHolder.newsImage)
                setNewsItemsOnClickListeners(viewHolder, position)
            }
            is DatelineViewHolder -> {
                val viewHolder: DatelineViewHolder = holder
                viewHolder.datelineText.text = newsItems[position - 1].title
            }
            else -> {
                val viewHolder: LoadingViewHolder = holder as LoadingViewHolder
                Glide.with(mLayoutInflater.context)
                    .load(R.drawable.loading)
                    .into(viewHolder.loadingImage)
            }
        }
    }

    private fun setAutoPlay(viewHolder: BannerViewHolder) {
        handler = MyHandler(
            viewHolder
        )
        mTimer = Timer()
        mTimer.schedule(object : TimerTask() {
            override fun run() {
                val message = Message()
                message.what = 0
                if (!isBannerScroll) {
                    handler.sendMessage(message)
                }
            }

        }, 3000, 3000)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setBannerListeners(viewHolder: BannerViewHolder) {
        viewHolder.banner.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_MOVE -> isBannerScroll = true
                MotionEvent.ACTION_DOWN -> isBannerScroll = false
                MotionEvent.ACTION_UP -> {
                    if (!isBannerScroll) {
                        val intent = Intent(mLayoutInflater.context, DetailActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        intent.putExtra(
                            "newsUrl",
                            topNewsItems[viewHolder.banner.currentItem - 1].url
                        )
                        mLayoutInflater.context.startActivity(intent)
                    }
                    isBannerScroll = false
                }
                else -> {

                }
            }
            false
        }
        viewHolder.banner.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                when (state) {
                    ViewPager.SCROLL_STATE_IDLE -> {
                        when (viewHolder.banner.currentItem) {
                            0 -> viewHolder.banner.setCurrentItem(
                                topNewsItems.size,
                                false
                            )
                            topNewsItems.size + 1 -> viewHolder.banner.setCurrentItem(
                                1,
                                false
                            )
                        }
                    }
                }
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
            }
        })
    }

    private fun setNewsItemsOnClickListeners(viewHolder: NewsItemsViewHolder, position: Int) {
        viewHolder.newsItem.setOnClickListener {
            val intent = Intent(mLayoutInflater.context, DetailActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("newsUrl", newsItems[position - 1].url)
            mLayoutInflater.context.startActivity(intent)
        }
    }

    fun resetTimer() {
        mTimer.cancel()
    }

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val banner: ViewPager = itemView.findViewById(R.id.banner)
    }

    class NewsItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsItem: CardView = itemView.findViewById(R.id.news_items)
        val newsTitle: TextView = itemView.findViewById(R.id.news_title)
        val newsHint: TextView = itemView.findViewById(R.id.news_hint)
        val newsImage: ImageView = itemView.findViewById(R.id.news_image)
    }

    class DatelineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val datelineText: TextView = itemView.findViewById(R.id.dateline_text)
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val loadingImage: ImageView = itemView.findViewById(R.id.loading_image)
    }

    class MyHandler(private val viewHolder: BannerViewHolder) : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0 -> viewHolder.banner.currentItem = viewHolder.banner.currentItem + 1
            }
        }
    }
}

