package com.nanbo.ordersystem.entity.EateryOrderSystem;

public class Test2 {
    private int id;
    private String content;
    private String created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Test2{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}
