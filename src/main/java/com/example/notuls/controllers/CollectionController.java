package com.example.notuls.controllers;

import com.example.notuls.controllers.dtos.requests.CreateCollectionRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.services.interfaces.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("collection")
public class CollectionController {

    @Autowired
    private ICollectionService service;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getCollectionByUser(@PathVariable Long id){
        BaseResponse baseResponse = service.getCollectionByUserId(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateCollectionRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping
    public void delete(@PathVariable Long id){service.delete(id);}
}
