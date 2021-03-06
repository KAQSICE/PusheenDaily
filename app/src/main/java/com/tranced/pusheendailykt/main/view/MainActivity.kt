package com.tranced.pusheendailykt.main.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.orhanobut.hawk.Hawk
import com.tranced.pusheendailykt.R
import com.tranced.pusheendailykt.main.presenter.Presenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * MainActivity
 * @author TranceD
 */
class MainActivity : AppCompatActivity(), IView {
    private val presenter = Presenter()
    private lateinit var toolbar: Toolbar
    private lateinit var toolbarDay: TextView
    private lateinit var toolbarMonth: TextView
    private lateinit var toolbarTitleText: TextView
    private lateinit var newsList: RecyclerView
    private lateinit var newsListAdapter: NewsListAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initToolbar()
        initNewsList()
        initSwipeRefreshLayout()

    }

    override fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        layoutInflater.inflate(R.layout.main_toolbar, toolbar)
        toolbarDay = findViewById(R.id.toolbar_day)
        toolbarMonth = findViewById(R.id.toolbar_month)
        toolbarTitleText = findViewById(R.id.toolbar_title_text)
        toolbarDay.text = getDay()
        toolbarMonth.text = getMonth()
        toolbarTitleText.text = getTitleText()
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override fun initNewsList(): RecyclerView = runBlocking {
        Hawk.init(baseContext).build()
        newsList = findViewById(R.id.news_list)
        val job1 = GlobalScope.launch {
            presenter.getBothItems()
            while (true) {
                if (!presenter.newsItems.isNullOrEmpty() && !presenter.topNewsItems.isNullOrEmpty()) {
                    break
                }
            }
        }
        job1.join()
        newsListAdapter = NewsListAdapter(
            baseContext,
            presenter.newsItems,
            presenter.topNewsItems
        )
        newsList.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = newsListAdapter
            addOnScrollListener(object : OnLoadingListener() {
                override fun onLoading() {
                    runBlocking {
                        val formerSize = presenter.newsItems?.size
                        val job2 = GlobalScope.launch {
                            presenter.getNewsItems()
                            while (true) {
                                if (presenter.newsItems?.size!! > formerSize!! + 1) {
                                    break
                                }
                            }
                        }
                        job2.join()
                        newsListAdapter.apply {
                            resetTimer()
                            notifyDataSetChanged()
                        }
                    }
                }
            })
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override fun initSwipeRefreshLayout() {
        swipeRefreshLayout = findViewById(R.id.refresh)
        swipeRefreshLayout.setOnRefreshListener {
            runBlocking {
                presenter.resetData()
                val job3 = GlobalScope.launch {
                    presenter.getBothItems()
                    while (true) {
                        if (!presenter.newsItems.isNullOrEmpty() && !presenter.topNewsItems.isNullOrEmpty()) {
                            break
                        }
                    }
                }
                job3.join()

                newsListAdapter =
                    NewsListAdapter(
                        baseContext,
                        presenter.newsItems,
                        presenter.topNewsItems
                    )
                newsList.adapter = newsListAdapter
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun getDay(): String {
        return presenter.getDay()
    }

    override fun getMonth(): String {
        return presenter.getMonth()
    }

    override fun getTitleText(): String {
        return presenter.getTitleText()
    }
}
