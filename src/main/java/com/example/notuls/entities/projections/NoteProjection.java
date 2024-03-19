package com.example.notuls.entities.projections;

import java.time.LocalDateTime;

public interface NoteProjection {

    String getTitle();

    String getDescription();

    Boolean getPinned();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
