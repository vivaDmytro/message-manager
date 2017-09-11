package org.dmytro.manager.service;

import org.dmytro.manager.MessageManagerApplicationTests;
import org.dmytro.manager.dto.Message;
import org.dmytro.manager.entity.MessageEntity;
import org.dmytro.manager.entity.UserEntity;
import org.dmytro.manager.exception.BusinessLogicException;
import org.dmytro.manager.repository.DbMessageRepository;
import org.dmytro.manager.repository.DbUserRepository;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

public class DbMessageServiceTest extends MessageManagerApplicationTests {

    @Mock
    private DbMessageRepository dbMessageRepository;

    @Mock
    private DbUserRepository dbUserRepository;

    @Test
    public void testFindAllWhenNoDbRecordsPresent() {
        when(dbMessageRepository.findAll()).thenReturn(Collections.emptyList());
        DbMessageService dbMessageService = new DbMessageService(dbMessageRepository, dbUserRepository);

        List<Message> messages = dbMessageService.findAll();

        assertTrue(messages.isEmpty());
    }

    @Test
    public void testResultIsSorted() {
        when(dbMessageRepository.findAll()).thenReturn(getMessageEntities());

        DbMessageService dbMessageService = new DbMessageService(dbMessageRepository, dbUserRepository);

        List<Message> messages = dbMessageService.findAll();

        assertEquals("Alex", messages.get(0).getUser());
        assertEquals("Bob", messages.get(1).getUser());
        assertEquals("Zoe", messages.get(2).getUser());
    }

    @Test(expected = BusinessLogicException.class)
    public void testUpdateWhenMessageIdIsNotPresent() {
        DbMessageService dbMessageService = new DbMessageService(dbMessageRepository, dbUserRepository);
        dbMessageService.update(new Message());
    }

    @Test(expected = BusinessLogicException.class)
    public void testUpdateWhenUserNameIsNotPresentInDb() {
        DbMessageService dbMessageService = new DbMessageService(dbMessageRepository, dbUserRepository);

        Message message = new Message();
        message.setId(0);

        dbMessageService.update(message);
    }

    @Test(expected = BusinessLogicException.class)
    public void testUpdateWhenMessageIsNotPresentInDb() {
        when(dbUserRepository.findOne(anyInt())).thenReturn(new UserEntity());
        DbMessageService dbMessageService = new DbMessageService(dbMessageRepository, dbUserRepository);

        Message message = new Message();
        message.setId(0);

        dbMessageService.update(message);
    }

    private List<MessageEntity> getMessageEntities() {
        MessageEntity messageEntity1 = new MessageEntity();
        messageEntity1.setId(0);
        messageEntity1.setText("Message 1");

        UserEntity user1 = new UserEntity();
        user1.setName("Bob");

        messageEntity1.setUser(user1);

        MessageEntity messageEntity2 = new MessageEntity();
        messageEntity2.setId(1);
        messageEntity2.setText("Message 2");

        UserEntity user2 = new UserEntity();
        user2.setName("Alex");

        messageEntity2.setUser(user2);

        MessageEntity messageEntity3 = new MessageEntity();
        messageEntity3.setId(2);
        messageEntity3.setText("Message 3");

        UserEntity user3 = new UserEntity();
        user3.setName("Zoe");

        messageEntity3.setUser(user3);

        return Arrays.asList(messageEntity1, messageEntity2, messageEntity3);
    }

}