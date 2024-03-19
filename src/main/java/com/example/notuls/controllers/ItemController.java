package com.example.notuls.controllers;

import com.example.notuls.controllers.dtos.requests.CreateItemRequest;
import com.example.notuls.controllers.dtos.requests.UpdateItemRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.services.interfaces.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    private IItemService service;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getItemByCollectionId(@PathVariable Long id){
        BaseResponse baseResponse = service.getItemByUserId(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateItemRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody UpdateItemRequest request){
        BaseResponse baseResponse = service.update(id,request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
