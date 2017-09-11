package org.dmytro.manager.repository;

import org.dmytro.manager.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;

public interface DbMessageRepository extends CrudRepository<MessageEntity, Integer> {
}
