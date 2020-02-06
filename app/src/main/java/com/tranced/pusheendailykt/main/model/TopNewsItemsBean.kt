package com.tranced.pusheendailykt.main.model

/**
 * TopNewsItemsBean
 * @author TranceD
 */
class TopNewsItemsBean {
    /**
     * date : 20200202
     * stories : [{"image_hue":"0xb3816b","title":"为什么叔本华认为年轻人很早洞察人事、谙于世故预示着本性平庸？","url":"https://daily.zhihu.com/story/9719864","hint":"曾加 · 2 分钟阅读","ga_prefix":"020211","images":["https://pic4.zhimg.com/v2-89b192a671f7a6fe9c49b8233d84028f.jpg"],"type":0,"id":9719864},{"image_hue":"0x3b89aa","title":"反社会人格有哪些不可思议的行为？","url":"https://daily.zhihu.com/story/9719859","hint":"子鸫 · 1 分钟阅读","ga_prefix":"020209","images":["https://pic2.zhimg.com/v2-3013b872e8a37b6d543176cc6b8ae331.jpg"],"type":0,"id":9719859},{"image_hue":"0x392e25","title":"当地时间 1 月 31 日英国正式脱离欧盟，将对世界和中国产生哪些影响？","url":"https://daily.zhihu.com/story/9719844","hint":"一个kebab · 2 分钟阅读","ga_prefix":"020207","images":["https://pic2.zhimg.com/v2-398e1d9650961a4e73884196fe164851.jpg"],"type":0,"id":9719844}]
     * top_stories : [{"image_hue":"0xb38d7d","hint":"作者 / 像少年拉菲迟","url":"https://daily.zhihu.com/story/9719812","image":"https://pic2.zhimg.com/v2-9f5fcb10d94f86a56dfbf4c69d7fdbb9.jpg","title":"小事 · 再也回不去的篮球场","ga_prefix":"020122","type":0,"id":9719812},{"image_hue":"0x34454a","hint":"作者 / Zpuzzle","url":"https://daily.zhihu.com/story/9719798","image":"https://pic3.zhimg.com/v2-7fd42dc941768d7b1b1e7c81962eef0a.jpg","title":"为什么会有文学鄙视链的存在，网络文学真的难登大雅之堂吗？","ga_prefix":"013116","type":0,"id":9719798},{"image_hue":"0x332424","hint":"作者 / 殷守甫","url":"https://daily.zhihu.com/story/9719668","image":"https://pic1.zhimg.com/v2-6b7b2e9f51b37d358173e75ea1f18758.jpg","title":"为什么黑格尔认为中国没有历史？","ga_prefix":"012809","type":0,"id":9719668},{"image_hue":"0x755339","hint":"作者 / 大象公会","url":"https://daily.zhihu.com/story/9719186","image":"https://pic1.zhimg.com/v2-e77e92a291d8c8c656a92878c7a30660.jpg","title":"刷快手与看莎士比亚有什么差别？","ga_prefix":"011907","type":0,"id":9719186},{"image_hue":"0x3c281c","hint":"作者 / 大鹏人文","url":"https://daily.zhihu.com/story/9719173","image":"https://pic4.zhimg.com/v2-c5fa85c09d2c283064e10acb8b644393.jpg","title":"世界上活得最悲催的哺乳动物是谁？","ga_prefix":"011820","type":0,"id":9719173}]
     */
    var date: String? = null
    var stories: List<StoriesBean>? = null
    var top_stories: List<TopStoriesBean>? = null

    class StoriesBean {
        /**
         * image_hue : 0xb3816b
         * title : 为什么叔本华认为年轻人很早洞察人事、谙于世故预示着本性平庸？
         * url : https://daily.zhihu.com/story/9719864
         * hint : 曾加 · 2 分钟阅读
         * ga_prefix : 020211
         * images : ["https://pic4.zhimg.com/v2-89b192a671f7a6fe9c49b8233d84028f.jpg"]
         * type : 0
         * id : 9719864
         */
        var image_hue: String? = null
        var title: String? = null
        var url: String? = null
        var hint: String? = null
        var ga_prefix: String? = null
        var type = 0
        var id = 0
        var images: List<String>? = null

    }

    class TopStoriesBean {
        /**
         * image_hue : 0xb38d7d
         * hint : 作者 / 像少年拉菲迟
         * url : https://daily.zhihu.com/story/9719812
         * image : https://pic2.zhimg.com/v2-9f5fcb10d94f86a56dfbf4c69d7fdbb9.jpg
         * title : 小事 · 再也回不去的篮球场
         * ga_prefix : 020122
         * type : 0
         * id : 9719812
         */
        var image_hue: String? = null
        var hint: String? = null
        var url: String? = null
        var image: String? = null
        var title: String? = null
        var ga_prefix: String? = null
        var type = 0
        var id = 0

    }
}
