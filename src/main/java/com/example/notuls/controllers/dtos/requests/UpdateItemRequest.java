package com.example.notuls.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateItemRequest {

    private String name;

    private String image;

    private String description;

    private float price;
}
