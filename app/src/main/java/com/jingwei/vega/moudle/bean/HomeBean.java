package com.jingwei.vega.moudle.bean;

import java.util.List;

public class HomeBean {
    private String title;
    private List<CardBean> mCardBeans;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CardBean> getCardBeans() {
        return mCardBeans;
    }

    public void setCardBeans(List<CardBean> cardBeans) {
        mCardBeans = cardBeans;
    }

    public static class CardBean{
        private String url;
        private String name;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
