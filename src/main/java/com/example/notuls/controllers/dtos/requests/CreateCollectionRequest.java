package com.example.notuls.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCollectionRequest {

    private String image;

    private String name;

    private Long userId;
}
