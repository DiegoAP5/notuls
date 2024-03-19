package com.example.notuls.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateNoteRequest {

    private String title;

    private String description;

    private Boolean pinned;
}
