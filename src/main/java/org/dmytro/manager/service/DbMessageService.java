package org.dmytro.manager.service;

import org.dmytro.manager.dto.Message;
import org.dmytro.manager.entity.MessageEntity;
import org.dmytro.manager.entity.UserEntity;
import org.dmytro.manager.exception.BusinessLogicException;
import org.dmytro.manager.repository.DbMessageRepository;
import org.dmytro.manager.repository.DbUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service("db")
@Transactional
public class DbMessageService implements MessageService {

    private Logger logger = LoggerFactory.getLogger(DbMessageService.class);

    private DbMessageRepository dbMessageRepository;
    private DbUserRepository dbUserRepository;

    @Autowired
    public DbMessageService(DbMessageRepository dbMessageRepository, DbUserRepository dbUserRepository) {
        this.dbMessageRepository = dbMessageRepository;
        this.dbUserRepository = dbUserRepository;
    }

    @Override
    public List<Message> findAll() {
        Iterable<MessageEntity> messages = dbMessageRepository.findAll();
        if (!messages.iterator().hasNext()) {
            logger.info("No messages were found");
            return Collections.emptyList();
        }
        return StreamSupport.stream(messages.spliterator(), false)
                .map(m -> new Message(m.getId(), m.getText(), m.getUser().getName()))
                .sorted(Comparator.comparing(Message::getUser))
                .collect(toList());
    }

    @Override
    public Message save(Message message) {
        UserEntity user = dbUserRepository.findByName(message.getUser());

        if (user == null) {
            UserEntity newUser = new UserEntity();
            newUser.setName(message.getUser());
            logger.info("Generating a new user '{}'", message.getUser());
            user = dbUserRepository.save(newUser);
        }

        MessageEntity newMessage = new MessageEntity();
        newMessage.setText(message.getText());
        newMessage.setUser(user);

        newMessage = dbMessageRepository.save(newMessage);

        return new Message(newMessage.getId(), newMessage.getText(), message.getUser());
    }

    @Override
    public Message update(Message message) {
        if (message.getId() == null) {
            logger.error("Message id isn't specified");
            throw new BusinessLogicException("Message id isn't specified");
        }
        UserEntity user = dbUserRepository.findByName(message.getUser());

        if (user == null) {
            logger.error("User '{}' doesn't exist", "User '", message.getUser());
            throw new BusinessLogicException("User '" + message.getUser() + "' doesn't exist");
        }

        MessageEntity existingMessage = dbMessageRepository.findOne(message.getId());

        if (existingMessage == null) {
            logger.error("Message with id '{}' doesn't exist", message.getId());
            throw new BusinessLogicException("Message with id '" + message.getId() + "' doesn't exist");
        }

        existingMessage.setText(message.getText());

        existingMessage = dbMessageRepository.save(existingMessage);

        return new Message(existingMessage.getId(), existingMessage.getText(), existingMessage.getUser().getName());
    }

    @Override
    public void delete(Integer id) {
        dbMessageRepository.delete(id);
    }
}
