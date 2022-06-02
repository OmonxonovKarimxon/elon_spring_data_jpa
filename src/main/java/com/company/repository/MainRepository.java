package com.company.repository;

import com.company.entity.PostEntity;
import com.company.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MainRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findByPhoneNumber(String phone);

}
