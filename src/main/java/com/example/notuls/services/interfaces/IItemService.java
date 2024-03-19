package com.example.notuls.services.interfaces;

import com.example.notuls.controllers.dtos.requests.CreateItemRequest;
import com.example.notuls.controllers.dtos.requests.UpdateItemRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.entities.Item;

public interface IItemService {

    Item findItemById(Long id);

    BaseResponse getItemByUserId(Long id);

    BaseResponse create(CreateItemRequest request);

    BaseResponse update(Long id, UpdateItemRequest request);

    void delete(Long id);
}
