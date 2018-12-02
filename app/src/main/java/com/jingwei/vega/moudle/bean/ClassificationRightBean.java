package com.jingwei.vega.moudle.bean;

import java.util.List;

public class ClassificationRightBean {
    private String title;
    private List<Bean> mUrlList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Bean> getUrlList() {
        return mUrlList;
    }

    public void setUrlList(List<Bean> urlList) {
        mUrlList = urlList;
    }

    public static class Bean {
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

