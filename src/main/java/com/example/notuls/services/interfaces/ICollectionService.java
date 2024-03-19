package com.example.notuls.services.interfaces;

import com.example.notuls.controllers.dtos.requests.CreateCollectionRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.entities.Collection;

public interface ICollectionService {

    Collection findCollectionById(Long id);

    BaseResponse getCollectionByUserId(Long id);

    BaseResponse create(CreateCollectionRequest request);

    void delete(Long id);
}
