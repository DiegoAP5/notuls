package com.example.notuls.services;

import com.example.notuls.controllers.dtos.requests.CreateCollectionRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.controllers.dtos.responses.CollectionResponse;
import com.example.notuls.controllers.excepcion.Excepcion;
import com.example.notuls.entities.Collection;
import com.example.notuls.entities.User;
import com.example.notuls.entities.projections.CollectionProjection;
import com.example.notuls.repositories.ICollectionRepository;
import com.example.notuls.services.interfaces.ICollectionService;
import com.example.notuls.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionServiceImpl implements ICollectionService {

    @Autowired
    private ICollectionRepository repository;

    @Autowired
    private IUserService userService;

    @Override
    public Collection findCollectionById(Long id) {
        return repository.findById(id).orElseThrow(() -> new Excepcion("Not found"));
    }

    @Override
    public BaseResponse getCollectionByUserId(Long id) {
        List<CollectionResponse> response = repository.getCollectionByUser(id).stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Collections by user")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateCollectionRequest request) {
        Collection collection = new Collection();
        collection = create(request, collection);
        CollectionResponse response = from(repository.save(collection));
        return BaseResponse.builder()
                .data(response)
                .message("Collection created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private CollectionResponse from(Collection collection){
        CollectionResponse response = new CollectionResponse();
        response.setImage(collection.getImage());
        response.setName(collection.getName());
        return response;
    }

    private CollectionResponse from(CollectionProjection collection){
        CollectionResponse response = new CollectionResponse();
        response.setName(collection.getName());
        response.setImage(collection.getImage());
        return response;
    }

    private Collection create(CreateCollectionRequest request, Collection collection) {
        User user = userService.findUserById(request.getUserId());
        collection.setName(request.getName());
        collection.setImage(request.getImage());
        collection.setCreated_at(LocalDateTime.now());
        collection.setUpdated_at(LocalDateTime.now());
        collection.setUser(user);
        return collection;
    }

}
