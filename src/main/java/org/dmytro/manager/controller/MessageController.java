package org.dmytro.manager.controller;

import org.dmytro.manager.dto.Message;
import org.dmytro.manager.exception.MessageManagerException;
import org.dmytro.manager.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@RestController
public class MessageController {

    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    private MessageService messageService;

    @Autowired
    public MessageController(@Qualifier("embedded") MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(path = "/messages", produces = "application/json")
    public List<Message> findAll() {

        logger.debug("Get all messages request received");
        return messageService.findAll();
    }

    @PostMapping(path = "/messages", produces = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Message save(@RequestBody @Valid Message message) {

        logger.debug("Create message {} request received", message);
        return messageService.save(message);
    }

    @PutMapping(path = "/messages", produces = "application/json")
    public Message update(@RequestBody @Valid Message message) {

        logger.debug("Update message {} request received", message);
        return messageService.update(message);
    }

    @DeleteMapping(path = "/messages/{id}", produces = "application/json")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {

        logger.debug("Delete message {} request received", id);
        messageService.delete(id);
    }

    @ExceptionHandler({MessageManagerException.class, ConstraintViolationException.class})
    public ResponseEntity<Error> businessError(Exception e) {
        logger.error("Business logic error {}", e.getMessage());
        return new ResponseEntity<>(
                new Error("Incorrect input"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleError(HttpServletRequest req, Exception ex) {
        logger.error("Request: {} raised {}", req.getRequestURL(), ex);
        return new ResponseEntity<>(
                new Error("Internal server error"), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
