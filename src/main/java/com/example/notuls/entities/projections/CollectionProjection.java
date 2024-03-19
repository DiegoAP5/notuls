package com.example.notuls.entities.projections;

import java.time.LocalDateTime;

public interface CollectionProjection {

    String getName();

    String getImage();

    LocalDateTime getUpdatedAt();
}
