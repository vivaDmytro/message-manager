package org.dmytro.manager.service;

import org.dmytro.manager.dto.Message;
import org.dmytro.manager.exception.BusinessLogicException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("embedded")
public class EmbeddedMessageService implements MessageService {

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public Message save(Message message) {
        return null;
    }

    @Override
    public Message update(Message message) {
        if (message.getId() == null) {
            throw new BusinessLogicException("Message id isn't specified");
        }

        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
