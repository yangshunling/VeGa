package com.jingwei.vega.moudle;

public class SearchMsgEvent {
    private String content;

    public SearchMsgEvent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
