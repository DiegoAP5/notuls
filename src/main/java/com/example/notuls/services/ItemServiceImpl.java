package com.example.notuls.services;

import com.example.notuls.controllers.dtos.requests.CreateItemRequest;
import com.example.notuls.controllers.dtos.requests.UpdateItemRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.controllers.dtos.responses.ItemResponse;
import com.example.notuls.controllers.excepcion.Excepcion;
import com.example.notuls.entities.Collection;
import com.example.notuls.entities.Item;
import com.example.notuls.entities.projections.ItemProjection;
import com.example.notuls.repositories.IItemRepository;
import com.example.notuls.services.interfaces.ICollectionService;
import com.example.notuls.services.interfaces.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private IItemRepository repository;

    @Autowired
    private ICollectionService collectionService;

    @Override
    public Item findItemById(Long id) {
        return repository.findById(id).orElseThrow(() -> new Excepcion("Not found"));
    }

    @Override
    public BaseResponse getItemByUserId(Long id) {
        List<ItemResponse> response = repository.getItemByUser(id).stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Items by collection")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateItemRequest request) {
        Item item = new Item();
        item = create(request, item);
        ItemResponse response = from(repository.save(item));
        return BaseResponse.builder()
                .data(response)
                .message("Item created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateItemRequest request) {
        Item item = findItemById(id);
        item = update(request, item);
        ItemResponse response = from(repository.save(item));
        return BaseResponse.builder()
                .data(response)
                .message("Updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ItemResponse from(Item item){
        ItemResponse response = new ItemResponse();
        response.setDescription(item.getDescription());
        response.setPrice(item.getPrice());
        response.setName(item.getName());
        response.setImage(item.getImage());
        return response;
    }

    private ItemResponse from(ItemProjection item){
        ItemResponse response = new ItemResponse();
        response.setDescription(item.getDescription());
        response.setName(item.getName());
        response.setImage(item.getImage());
        response.setPrice(item.getPrice());
        return response;
    }

    private Item create(CreateItemRequest request, Item item) {
        Collection collection = collectionService.findCollectionById(request.getCollectionId());
        item.setDescription(request.getDescription());
        item.setPrice(request.getPrice());
        item.setName(request.getName());
        item.setImage(request.getImage());
        item.setCreated_at(LocalDateTime.now());
        item.setUpdated_at(LocalDateTime.now());
        item.setCollection(collection);
        return item;
    }

    private Item update(UpdateItemRequest request, Item item){
        item.setDescription(request.getDescription());
        item.setName(request.getName());
        item.setImage(request.getImage());
        item.setPrice(request.getPrice());
        item.setUpdated_at(LocalDateTime.now());
        return item;
    }
}
