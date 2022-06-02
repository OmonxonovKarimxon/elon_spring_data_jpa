package com.company.controller;

import com.company.dto.PostDTO;
import com.company.dto.UserDto;
import com.company.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController {
@Autowired
private MainService mainService;

@PostMapping("create")
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
    mainService.create(userDto);
    return ResponseEntity.ok().build();
}
@GetMapping("/allPost")
    public ResponseEntity<?> allPost(){
     List<PostDTO> list =  mainService.allPost();
    return  ResponseEntity.ok().body(list);
}

}
