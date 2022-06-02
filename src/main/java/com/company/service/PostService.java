package com.company.service;

import com.company.dto.PostDTO;
import com.company.entity.PostEntity;
import com.company.entity.UserEntity;
import com.company.enums.PostStatus;
import com.company.exception.BadRequestException;
import com.company.exception.DontFoundUser;
import com.company.repository.MainRepository;
import com.company.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MainRepository mainRepository;

    public void create(PostDTO postDTO) {
        isValid(postDTO);
        Optional<UserEntity> optional = mainRepository.findByPhoneNumber(postDTO.getUserPhone());

        if (!optional.isPresent()) {
            throw new BadRequestException(" mazgi bizda bunaqa user yoqku");
        }
        PostEntity postEntity = new PostEntity();

        postEntity.setStatus(PostStatus.ACTIVE.name());
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setJoin_time(LocalDateTime.now());
        postEntity.setPrice(postDTO.getPrice());
        postEntity.setDescription(postDTO.getDescription());
        postEntity.setUserPhone(postDTO.getUserPhone());
        postRepository.save(postEntity);
    }


    private void isValid(PostDTO dto) {
        if (dto.getTitle() == null) {
            throw new BadRequestException("Name  xato.");
        }
        if (dto.getDescription() == null) {
            throw new BadRequestException("Surname required.");
        }
        if (dto.getPrice() == 0) {
            throw new BadRequestException("price is empty");
        }
    }


    public void update(Integer id, PostDTO postDTO) {
        isValid(postDTO);
        Optional<PostEntity> optional = postRepository.findByIdAndStatus(id,"ACTIVE");
        if (optional.isEmpty()) {
            throw new BadRequestException(" mazgi bunaqa post yoqku");
        }

        PostEntity postEntity = optional.get();
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setPrice(postDTO.getPrice());
        postEntity.setDescription(postDTO.getDescription());
        postRepository.save(postEntity);
    }

    public List<PostDTO> postList(String userPhone) {
        List<PostEntity> optional = postRepository.findByUserPhoneAndStatus(userPhone,"ACTIVE");

        if(optional.size() ==0){
            throw  new DontFoundUser("we have not this phone number");
        }

       List<PostDTO> postDTO = new ArrayList<>();
        optional.forEach(entity -> {
            PostDTO dto = new PostDTO();
            dto.setDescription(entity.getDescription());
            dto.setTitle(entity.getTitle());
            dto.setPrice(entity.getPrice());
            postDTO.add(dto);
        });

        return  postDTO;
    }


    public void delete(Integer id) {

        Optional<PostEntity> optional = postRepository.findByIdAndStatus(id, "ACTIVE");
        if (optional.isEmpty()) {
            throw new BadRequestException(" mazgi bunaqa idli post yoqku");
        }

        PostEntity postEntity = optional.get();
        postEntity.setStatus(PostStatus.DELETE.name());

        postRepository.save(postEntity);
    }
}
