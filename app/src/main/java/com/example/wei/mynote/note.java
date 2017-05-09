package com.example.wei.mynote;

/**
 * Created by Wei on 2017/5/3.
 */

public class note {
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    String top;
    public note(String content, String top) {
        this.content = content;
        this.top = top;
    }
public  note(){}

}
