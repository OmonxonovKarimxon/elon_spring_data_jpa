package com.company.repository;

import com.company.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<PostEntity, Integer> {
  //  Optional<PostEntity> findByUserPhone(String phone);

    List<PostEntity> findByUserPhoneAndStatus(String phone, String status);
    List<PostEntity> findByStatus(String status);
    Optional<PostEntity> findByIdAndStatus(Integer phone, String status);


}
