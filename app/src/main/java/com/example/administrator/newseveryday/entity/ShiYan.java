package com.example.administrator.newseveryday.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/8/1.
 */
public class ShiYan {

    /**
     * errNum : 0
     * errMsg : success
     * retData : [{"title":"专家:美或纠集日澳联合巡航南海 挑战中国主权","url":"http://w.huanqiu.com/r/MV8wXzkxNjQ2NjFfMjVfMTQ2ODM2ODQ4MA==","abstract":"裁决增加南海生战的可能性赵小卓美国人做了不少中美在南海开战的猜想与设想,\u201c恶劣\u201d的仲裁裁决,会增加南海生战的可能性吗?开战不符合任何一方的利益。从中国方面看,不论是构建中美新型大国关系,还是打造\u201c亲诚惠容\u201d命运共同体,都是为了争取一个和平安宁的周边安全环境。","image_url":""},{"title":"军报谈南海仲裁：属于我们的领土，我们寸土不让","url":"http://m.gmw.cn/toutiao/2016-07/13/content_114130536.htm","abstract":"本报评论员菲律宾南海仲裁案闹剧终于收场，其裁决令人震惊，令人愤慨。中国政府和中国人民对此坚决反对，绝不承认和接受。中国军队将坚定不移捍卫国家主权、安全和海洋权益，坚决维护地区和平稳定，应对各种威胁挑战。这场由菲律宾阿基诺三世政府单方提起的所谓仲裁，是披着法律外衣的政治挑衅。","image_url":""},{"title":"大兴，是个什么鬼地方","url":"http://toutiao.com/group/6305836792415830274/","abstract":"为啥我一看到西瓜就想到三个字：\u201c庞各庄\u201d？说了北京那么多区！大多都是给那些区刷存在感的！然而今天要讲的这个区怎么说呢？只能说：\u201c存在感很强，未来发展很耀眼，让别区很眼红！\u201d不是中心城区，不是人气王通州！是。。。大兴区！大兴区现在到底多牛X？小北用大兴呆了4年的经验客观的评述一下！","image_url":"http://p3.pstatp.com/list/a7700005cba14776349"},{"title":"《大鱼海棠》：细聊神话的开端","url":"http://toutiao.com/group/6305494785173143809/","abstract":"文/夜之帝王一部《大鱼海棠》耗时12年，虽然现在谁也不知道在商业上它会有多大的成功，但其中所出现的中国早期神话元素却是大有嚼头。鲲电影中的主角之一鲲，是一只额头长有独角的红色鲸鱼，同时在剧中也是人类灵魂的具象表现，生长速度惊人。这无疑是借鉴自古籍中所描述的巨兽\u2014\u2014鲲的概念。","image_url":"http://p3.pstatp.com/list/a3400085e0ef972eae8"},{"title":"看了日剧《OL开始当女公关》么？完全就是职场社交宝典呀","url":"http://toutiao.com/group/6305451344287957250/","abstract":"一口气看了三集日剧《OL开始当女公关》，太爽了！虽然这里的公关不是指咱们说的PR，可里面很多社交心机还是很有趣的，可以通用！真的是一个非常清纯的夜总会陪酒女的故事。看完之后，不仅仅可以修炼职场社交宝典，尤其是各种防止性骚扰的聪明小贴士，各位痴情少女还可以修炼撩汉大法！","image_url":"http://p3.pstatp.com/list/9e10008f359ac312160"}]
     */

    private int errNum;
    private String errMsg;
    /**
     * title : 专家:美或纠集日澳联合巡航南海 挑战中国主权
     * url : http://w.huanqiu.com/r/MV8wXzkxNjQ2NjFfMjVfMTQ2ODM2ODQ4MA==
     * abstract : 裁决增加南海生战的可能性赵小卓美国人做了不少中美在南海开战的猜想与设想,“恶劣”的仲裁裁决,会增加南海生战的可能性吗?开战不符合任何一方的利益。从中国方面看,不论是构建中美新型大国关系,还是打造“亲诚惠容”命运共同体,都是为了争取一个和平安宁的周边安全环境。
     * image_url :
     */

    private List<RetDataBean> retData;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<RetDataBean> getRetData() {
        return retData;
    }

    public void setRetData(List<RetDataBean> retData) {
        this.retData = retData;
    }

    public static class RetDataBean {
        private String title;
        private String url;
        @SerializedName("abstract")
        private String abstractX;
        private String image_url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAbstractX() {
            return abstractX;
        }

        public void setAbstractX(String abstractX) {
            this.abstractX = abstractX;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}
