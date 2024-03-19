package com.example.notuls.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoteResponse {

    private String title;

    private String description;

    private Boolean pinned;
}
