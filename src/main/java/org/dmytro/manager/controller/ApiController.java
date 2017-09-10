package org.dmytro.manager.controller;

import org.dmytro.manager.bean.Message;
import org.dmytro.manager.bean.User;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ApiController {

    @GetMapping("/user")
    public User getUser() {
        return new User(0, "TEST");
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {

        return Arrays.asList(
                new Message(0,"hello", "dmytro"),
                new Message(1,"how are you?", "dmytro"),
                new Message(2,"hi!", "bill")
                );
    }

    @DeleteMapping("/messages/{id}")
    public void delete(@PathVariable String id) {
        System.out.println(id);

    }

    @PutMapping("/message")
    public Message update(@RequestBody Message message) {
        return message;
    }

    @PostMapping("/message")
    public Message save(@RequestBody Message message) {
        message.setId(5);
        return message;
    }
}
