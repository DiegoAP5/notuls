package com.example.notuls.entities.projections;

import java.time.LocalDateTime;

public interface ItemProjection {

    String getName();

    String getDescription();

    String getImage();

    float getPrice();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
