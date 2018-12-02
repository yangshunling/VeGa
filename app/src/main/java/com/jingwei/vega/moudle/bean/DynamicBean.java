package com.jingwei.vega.moudle.bean;

import java.util.List;

public class DynamicBean {
    private String image;
    private String name;
    private String content;
    private List<String> mUrlList;
    private String time;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getUrlList() {
        return mUrlList;
    }

    public void setUrlList(List<String> urlList) {
        mUrlList = urlList;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

