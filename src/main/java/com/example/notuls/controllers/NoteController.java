package com.example.notuls.controllers;

import com.example.notuls.controllers.dtos.requests.CreateNoteRequest;
import com.example.notuls.controllers.dtos.requests.UpdateNoteRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.services.interfaces.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("note")
public class NoteController {

    @Autowired
    private INoteService service;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getNoteByUser(@PathVariable Long id){
        BaseResponse baseResponse = service.getNoteByUserId(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateNoteRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody UpdateNoteRequest request){
        BaseResponse baseResponse = service.update(id,request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){service.delete(id);}
}
