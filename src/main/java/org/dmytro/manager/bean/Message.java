package org.dmytro.manager.bean;

public class Message {

    private Integer id;
    private String text;
    private String user;

    public Message() {
    }

    public Message(Integer id, String text, String user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
