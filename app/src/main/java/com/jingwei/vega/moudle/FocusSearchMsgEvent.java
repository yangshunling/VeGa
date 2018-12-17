package com.jingwei.vega.moudle;

public class FocusSearchMsgEvent {
    private String content;

    public FocusSearchMsgEvent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
