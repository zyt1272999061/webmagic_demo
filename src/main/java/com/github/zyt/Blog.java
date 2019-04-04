package com.github.zyt;

/**
 * @Author: zyt
 * @Date: 2019/4/1 10:15
 * @Description:
 */
public class Blog {

    int key;

    String title;

    String content;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
