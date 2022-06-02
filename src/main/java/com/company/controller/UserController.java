package com.company.controller;

import com.company.dto.PostDTO;
import com.company.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/post")
public class UserController {
    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PostDTO postDTO) {
        postService.create(postDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id  ,@RequestBody PostDTO postDTO) {
        postService.update(id, postDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("postList/{userPhone}")
    public ResponseEntity<?> postList(@PathVariable("userPhone") String userPhone){
       List<PostDTO> list =  postService.postList(userPhone);
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id") Integer id){
        postService.delete(id);
        return ResponseEntity.ok().build();
    }
}
