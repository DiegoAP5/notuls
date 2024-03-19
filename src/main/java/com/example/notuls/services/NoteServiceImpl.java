package com.example.notuls.services;

import com.example.notuls.controllers.dtos.requests.CreateNoteRequest;
import com.example.notuls.controllers.dtos.requests.UpdateNoteRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.controllers.dtos.responses.NoteResponse;
import com.example.notuls.controllers.excepcion.Excepcion;
import com.example.notuls.entities.Note;
import com.example.notuls.entities.User;
import com.example.notuls.entities.projections.NoteProjection;
import com.example.notuls.repositories.INoteRepository;
import com.example.notuls.services.interfaces.INoteService;
import com.example.notuls.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements INoteService {

    @Autowired
    private INoteRepository repository;

    @Autowired
    private IUserService userService;

    @Override
    public Note getNoteById(Long id) {
        return repository.findById(id).orElseThrow(() -> new Excepcion("Note not found"));
    }

    @Override
    public BaseResponse getNoteByUserId(Long id) {
        List<NoteResponse> response = repository.listAllNotesByUser(id).stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Notes by user id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateNoteRequest request) {
        Note note = new Note();
        note = create(request,note);
        NoteResponse response = from(repository.save(note));
        return BaseResponse.builder()
                .data(response)
                .message("Note created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateNoteRequest request) {
        Note note = getNoteById(id);
        note = update(request, note);
        NoteResponse response = from(repository.save(note));
        return BaseResponse.builder()
                .data(response)
                .message("Note updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private NoteResponse from(Note note){
        NoteResponse response = new NoteResponse();
        response.setDescription(note.getDescription());
        response.setTitle(note.getTitle());
        response.setPinned(note.getPinned());
        return response;
    }

    private NoteResponse from(NoteProjection note){
        NoteResponse response = new NoteResponse();
        response.setPinned(note.getPinned());
        response.setDescription(note.getDescription());
        response.setTitle(note.getTitle());
        return response;
    }

    private Note create(CreateNoteRequest request, Note note) {
        User user = userService.findUserById(request.getUserId());
        note.setDescription(request.getDescription());
        note.setTitle(request.getTitle());
        note.setPinned(request.getPinned());
        note.setCreated_at(LocalDateTime.now());
        note.setUpdated_at(LocalDateTime.now());
        note.setUser(user);
        return note;
    }

    private Note update(UpdateNoteRequest request, Note note){
        note.setDescription(request.getDescription());
        note.setPinned(request.getPinned());
        note.setTitle(request.getTitle());
        note.setUpdated_at(LocalDateTime.now());
        return note;
    }
}
