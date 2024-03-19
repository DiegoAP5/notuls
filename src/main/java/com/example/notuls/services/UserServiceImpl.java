package com.example.notuls.services;

import com.example.notuls.controllers.dtos.requests.CreateUserRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.controllers.dtos.responses.UserReponse;
import com.example.notuls.controllers.excepcion.Excepcion;
import com.example.notuls.entities.User;
import com.example.notuls.repositories.IUserRepository;
import com.example.notuls.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;
    @Override
    public User findUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new Excepcion("User not found"));
    }

    @Override
    public BaseResponse listUsers() {
        List<UserReponse> responses = repository.findAll().stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(responses)
                .message("User list")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getUserById(Long id) {
        UserReponse response = from(findUserById(id));
        return BaseResponse.builder()
                .data(response)
                .message("User")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateUserRequest request) {
        User user = new User();
        user = create(request,user);
        UserReponse response = from(repository.save(user));
        return BaseResponse.builder()
                .data(response)
                .message("User created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private UserReponse from(User user){
        UserReponse response = new UserReponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setId(user.getId());
        response.setLastname(user.getLastname());
        return response;
    }

    private User create(CreateUserRequest request, User user) {
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setLastname(request.getLastname());
        return user;
    }
}
