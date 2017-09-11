package org.dmytro.manager.controller;

import com.google.gson.Gson;
import org.dmytro.manager.MessageManagerApplicationTests;
import org.dmytro.manager.dto.Message;
import org.dmytro.manager.service.MessageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MessageControllerTest extends MessageManagerApplicationTests {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    private MockMvc mockMvc;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
    }

    @Test
    public void testFindAllMapping() throws Exception {

        when(messageService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(
                get("/messages/"))
                .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void testSaveMapping() throws Exception {

        Message message = new Message(0, "text", "test");

        when(messageService.save(message)).thenReturn(message);

        mockMvc.perform(
                post("/messages/")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new Gson().toJson(message)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void testUpdateMapping() throws Exception {

        Message message = new Message(0, "text", "test");

        when(messageService.update(message)).thenReturn(message);

        mockMvc.perform(
                put("/messages/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new Gson().toJson(message)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void testDeleteMapping() throws Exception {

        mockMvc.perform(
                delete("/messages/1"))
                .andExpect(status().isNoContent());
    }

}