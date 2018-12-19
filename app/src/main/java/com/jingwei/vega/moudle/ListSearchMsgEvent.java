package com.jingwei.vega.moudle;

public class ListSearchMsgEvent {
    private String content;

    public ListSearchMsgEvent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
