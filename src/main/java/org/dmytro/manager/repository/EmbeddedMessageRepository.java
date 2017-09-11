package org.dmytro.manager.repository;

import org.dmytro.manager.dto.Message;

import java.util.List;

public interface EmbeddedMessageRepository {

    List<Message> findAll();

    Message save(Message message);

    Message update(Message message);

    void delete(Integer id);

}
