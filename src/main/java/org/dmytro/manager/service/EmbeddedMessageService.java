package org.dmytro.manager.service;

import org.dmytro.manager.dto.Message;
import org.dmytro.manager.exception.BusinessLogicException;
import org.dmytro.manager.repository.EmbeddedMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service("embedded")
public class EmbeddedMessageService implements MessageService {

    private EmbeddedMessageRepository embeddedMessageRepository;

    @Autowired
    public EmbeddedMessageService(EmbeddedMessageRepository embeddedMessageRepository) {
        this.embeddedMessageRepository = embeddedMessageRepository;
    }

    @Override
    public List<Message> findAll() {
        return embeddedMessageRepository.findAll().stream()
                .sorted(comparing(Message::getUser))
                .collect(toList());
    }

    @Override
    public Message save(Message message) {
        return embeddedMessageRepository.save(message);
    }

    @Override
    public Message update(Message message) {
        if (message.getId() == null) {
            throw new BusinessLogicException("Message id isn't specified");
        }
        return embeddedMessageRepository.update(message);
    }

    @Override
    public void delete(Integer id) {
        embeddedMessageRepository.delete(id);
    }
}
