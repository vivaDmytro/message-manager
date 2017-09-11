package org.dmytro.manager.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class Message {

    private Integer id;

    @NotNull(message = "Message shouldn't be null")
    @NotEmpty(message = "Message shouldn't be empty")
    private String text;

    @NotNull(message = "User name shouldn't be null")
    @NotEmpty(message = "User name shouldn't be empty")
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

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
