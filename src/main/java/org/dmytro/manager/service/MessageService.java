package org.dmytro.manager.service;

import org.dmytro.manager.dto.Message;

import java.util.List;

public interface MessageService {

    List<Message> findAll();

    Message save(Message message);

    Message update(Message message);

    void delete(Integer id);
}
