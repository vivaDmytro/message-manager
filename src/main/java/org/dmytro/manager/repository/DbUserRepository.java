package org.dmytro.manager.repository;

import org.dmytro.manager.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface DbUserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByName(String name);
}
