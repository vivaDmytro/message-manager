package org.dmytro.manager.repository;

import org.dmytro.manager.dto.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
public class EmbeddedMessageRepositoryImpl implements EmbeddedMessageRepository {

    private List<Message> messages = new ArrayList<>();

    @Override
    public List<Message> findAll() {
        return messages;
    }

    @Override
    public Message save(Message message) {
        message.setId(getId());
        messages.add(message);

        return message;
    }

    @Override
    public Message update(Message message) {
        messages.stream()
                .filter(m -> m.getId().equals(message.getId()))
                .findFirst()
                .ifPresent(ms -> ms.setText(message.getText()));

        return message;
    }

    @Override
    public void delete(Integer id) {
        messages = messages.stream()
                .filter(m -> !m.getId().equals(id))
                .collect(toList());
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    private int getId() {
        return messages.stream().max(Comparator.comparing(Message::getId))
                .map(message -> message.getId() + 1).orElse(0);
    }
}
