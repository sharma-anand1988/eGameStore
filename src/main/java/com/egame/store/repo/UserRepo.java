package com.egame.store.repo;

import org.springframework.data.repository.CrudRepository;

import com.egame.store.entity.UserEntity;

public interface UserRepo extends CrudRepository<UserEntity, Long>{

	UserEntity findByName(String username);

}
