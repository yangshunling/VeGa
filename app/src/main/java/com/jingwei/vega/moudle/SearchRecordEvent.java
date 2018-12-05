package com.jingwei.vega.moudle;

public class SearchRecordEvent {
    private String content;

    public SearchRecordEvent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
