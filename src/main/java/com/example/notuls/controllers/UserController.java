package com.example.notuls.controllers;

import com.example.notuls.controllers.dtos.requests.CreateUserRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getUserById(@PathVariable Long id){
        BaseResponse baseResponse = service.getUserById(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping
    public ResponseEntity<BaseResponse> listUser(){
        BaseResponse baseResponse = service.listUsers();
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateUserRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
