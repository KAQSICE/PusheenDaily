package com.tranced.pusheendailykt.main.model

/**
 * NewsItemsBean
 * @author TranceD
 */
class NewsItemsBean {
    /**
     * date : 20200123
     * stories : [{"image_hue":"0xb3977d","title":"针对新型肺炎，目前各省市采取了哪些疾控措施？","url":"https://daily.zhihu.com/story/9719443","hint":"急诊狼人 · 1 分钟阅读","ga_prefix":"012320","images":["https://pic3.zhimg.com/v2-287592fa4c6cf7e0d2a3ee14737cf91a.jpg"],"type":0,"id":9719443},{"image_hue":"0xb39d7d","title":"如何看待综艺节目「你怎么这么好看」被指加深对女性的刻板印象？","url":"https://daily.zhihu.com/story/9719383","hint":"何鲤游 · 2 分钟阅读","ga_prefix":"012316","images":["https://pic4.zhimg.com/v2-62b9a5ec3d04a04733ec0a6b3ebe585b.jpg"],"type":0,"id":9719383},{"image_hue":"0x7d8fb3","title":"为何普通话以北京话为基础？","url":"https://daily.zhihu.com/story/9719429","hint":"安森垚 · 14 分钟阅读","ga_prefix":"012311","images":["https://pic2.zhimg.com/v2-3fda1894e959cab398d579437ca3021d.jpg"],"type":0,"id":9719429},{"image_hue":"0x28202e","title":"如何评价「奇葩说」BBking 终极辩题「年轻人该不该裸辞」？","url":"https://daily.zhihu.com/story/9719426","hint":"小红拖拉机 · 4 分钟阅读","ga_prefix":"012309","images":["https://pic3.zhimg.com/v2-5c9a139fb20b21035c21f5b0318ddc72.jpg"],"type":0,"id":9719426},{"image_hue":"0x39595e","title":"武汉肺炎病原体被初步判定为新型冠状病毒，意味着什么？","url":"https://daily.zhihu.com/story/9719407","hint":"知乎用户 · 5 分钟阅读","ga_prefix":"012307","images":["https://pic1.zhimg.com/v2-38dee2ecb495bd55fd8aded64ed11564.jpg"],"type":0,"id":9719407},{"image_hue":"0xb3977d","title":"瞎扯 · 如何正确地吐槽","url":"https://daily.zhihu.com/story/9719384","hint":"VOL.2313","ga_prefix":"012306","images":["https://pic2.zhimg.com/v2-8b77de08d6f4e9c579d94e2bb9d9ae39.jpg"],"type":0,"id":9719384}]
     */
    var date: String? = null
    var stories: List<StoriesBean>? = null

    class StoriesBean(var type: Int, var title: String) {
        /**
         * image_hue : 0xb3977d
         * title : 针对新型肺炎，目前各省市采取了哪些疾控措施？
         * url : https://daily.zhihu.com/story/9719443
         * hint : 急诊狼人 · 1 分钟阅读
         * ga_prefix : 012320
         * images : ["https://pic3.zhimg.com/v2-287592fa4c6cf7e0d2a3ee14737cf91a.jpg"]
         * type : 0
         * id : 9719443
         */
        var image_hue: String? = null
        var url: String? = null
        var hint: String? = null
        var ga_prefix: String? = null
        var id = 0
        var images: List<String>? = null

    }
}