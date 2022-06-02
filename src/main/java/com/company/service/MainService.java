package com.company.service;

import com.company.dto.PostDTO;
import com.company.dto.UserDto;
import com.company.entity.PostEntity;
import com.company.entity.UserEntity;
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
public class MainService {
    @Autowired
    private MainRepository mainRepository;
    @Autowired
    private PostRepository postRepository;

    public void create(UserDto userDto) {
        Optional<UserEntity> optional = mainRepository.findByPhoneNumber(userDto.getPhoneNumber());
        if (optional.isPresent()) {
            throw new BadRequestException("Phone borku mazgi.");
        }
        UserEntity entity = new UserEntity();
        entity.setPhoneNumber(userDto.getPhoneNumber());
        entity.setSurname(userDto.getSurname());
        entity.setName(userDto.getName());
        entity.setPassword(userDto.getPassword());
        entity.setRegister_time(LocalDateTime.now());
        mainRepository.save(entity);
    }

    public List<PostDTO> allPost() {

        List<PostEntity> optional = postRepository.findByStatus("ACTIVE");

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
}
