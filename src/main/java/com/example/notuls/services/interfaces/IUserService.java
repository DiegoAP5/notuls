package com.example.notuls.services.interfaces;

import com.example.notuls.controllers.dtos.requests.CreateUserRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.entities.User;

public interface IUserService {

    User findUserById(Long id);

    BaseResponse listUsers();

    BaseResponse getUserById(Long id);

    BaseResponse create(CreateUserRequest request);

    void delete(Long id);
}
