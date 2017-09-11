package org.dmytro.manager.service;

import org.dmytro.manager.MessageManagerApplicationTests;
import org.dmytro.manager.dto.Message;
import org.dmytro.manager.repository.EmbeddedMessageRepositoryImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class EmbeddedMessageServiceTest extends MessageManagerApplicationTests {

    @Autowired
    private EmbeddedMessageRepositoryImpl embeddedMessageRepository;

    @Test
    public void testFindAllIsSorted() {

        embeddedMessageRepository.setMessages(getMessages());
        EmbeddedMessageService embeddedMessageService =
                new EmbeddedMessageService(embeddedMessageRepository);

        List<Message> messages = embeddedMessageService.findAll();

        assertEquals(3, messages.size());

        assertEquals("ann", messages.get(0).getUser());
        assertEquals("bob", messages.get(1).getUser());
        assertEquals("nick", messages.get(2).getUser());
    }

    @Test
    public void testSave() {

        embeddedMessageRepository.setMessages(new ArrayList<>());
        EmbeddedMessageService embeddedMessageService =
                new EmbeddedMessageService(embeddedMessageRepository);

        Message message1 = new Message();
        message1.setId(0);
        message1.setText("Message 1");
        message1.setUser("bob");

        embeddedMessageService.save(message1);

        assertEquals(1, embeddedMessageRepository.getMessages().size());

        Message message2 = new Message();
        message2.setId(0);
        message2.setText("Message 2");
        message2.setUser("nick");

        embeddedMessageService.save(message2);

        assertEquals(2, embeddedMessageRepository.getMessages().size());
    }

    @Test
    public void testUpdate() {

        Message message1 = new Message();
        message1.setId(0);
        message1.setText("Message 1");
        message1.setUser("bob");

        embeddedMessageRepository.setMessages(Collections.singletonList(message1));

        EmbeddedMessageService embeddedMessageService =
                new EmbeddedMessageService(embeddedMessageRepository);

        message1.setText("Updated message");

        embeddedMessageService.update(message1);

        assertEquals(1, embeddedMessageRepository.getMessages().size());
        assertEquals("Updated message", embeddedMessageRepository.getMessages().get(0).getText());
    }

    @Test
    public void testDelete() {

        Message message1 = new Message();
        message1.setId(0);
        message1.setText("Message 1");
        message1.setUser("bob");

        embeddedMessageRepository.setMessages(Collections.singletonList(message1));

        EmbeddedMessageService embeddedMessageService =
                new EmbeddedMessageService(embeddedMessageRepository);

        embeddedMessageService.delete(message1.getId());

        assertEquals(0, embeddedMessageRepository.getMessages().size());
    }

    private List<Message> getMessages() {
        Message message1 = new Message();
        message1.setId(0);
        message1.setText("Message 1");
        message1.setUser("bob");

        Message message2 = new Message();
        message2.setId(0);
        message2.setText("Message 2");
        message2.setUser("nick");

        Message message3 = new Message();
        message3.setId(0);
        message3.setText("Message 2");
        message3.setUser("ann");

        return Arrays.asList(message1, message2, message3);
    }

}