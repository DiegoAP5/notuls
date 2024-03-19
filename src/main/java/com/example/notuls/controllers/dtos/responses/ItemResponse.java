package com.example.notuls.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter @Setter
public class ItemResponse {

    private String name;

    private String image;

    private String description;

    private float price;
}
