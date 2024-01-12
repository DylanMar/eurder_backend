package com.switchfully.eurder_backend.controller;

import com.switchfully.eurder_backend.dto.itemdto.CreateItemDto;
import com.switchfully.eurder_backend.dto.itemdto.ItemDto;
import com.switchfully.eurder_backend.dto.itemdto.ItemResupplyUrgencyDto;
import com.switchfully.eurder_backend.dto.itemdto.UpdateItemDto;
import com.switchfully.eurder_backend.dto.itemgroupdto.ItemGroupDto;
import com.switchfully.eurder_backend.exception.AuthorizationException;
import com.switchfully.eurder_backend.service.AuthorizationService;
import com.switchfully.eurder_backend.service.ItemService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/item")
public class ItemController {

    private final AuthorizationService authorizationService;
    private final ItemService itemService;

    public ItemController(AuthorizationService authorizationService, ItemService itemService) {
        this.authorizationService = authorizationService;
        this.itemService = itemService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/filter={filter}", produces = "application/json")
//    @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
    public List<ItemResupplyUrgencyDto> getItems(@PathVariable String filter) {
//        if (!authorizationService.isAdmin(authorization)) {
//            throw new AuthorizationException("You are not authorized for this action");
//        }
        return itemService.getItems(filter);
    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(produces = "application/json")
//    public List<ItemGroupDto> getItemsShippingToday(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
//        if (!authorizationService.isAdmin(authorization)) {
//            throw new AuthorizationException("You are not authorized for this action");
//        }
//        return itemService.getItemsShippingToday();
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
//    public ItemDto createItem(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestBody CreateItemDto createItemDto) {
        public ItemDto createItem(@RequestBody CreateItemDto createItemDto) {
//        if (!authorizationService.isAdmin(authorization)) {
//            throw new AuthorizationException("You are not authorized for this action");
//        }
        return itemService.createItem(createItemDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ItemDto updateItem(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestBody UpdateItemDto updateItemDto, @PathVariable Long id) {
        if (!authorizationService.isAdmin(authorization)) {
            throw new AuthorizationException("You are not authorized for this action");
        }
        return itemService.updateItem(id, updateItemDto);
    }

}
