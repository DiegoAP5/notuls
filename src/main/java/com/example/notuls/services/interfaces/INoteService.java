package com.example.notuls.services.interfaces;

import com.example.notuls.controllers.dtos.requests.CreateNoteRequest;
import com.example.notuls.controllers.dtos.requests.UpdateNoteRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.entities.Note;

public interface INoteService {

    Note getNoteById(Long id);

    BaseResponse getNoteByUserId(Long id);

    BaseResponse create(CreateNoteRequest request);

    BaseResponse update(Long id, UpdateNoteRequest request);

    void delete(Long id);
}
