package com.switchfully.eurder_backend.dto.orderdto;

import com.switchfully.eurder_backend.dto.itemgroupdto.CreateItemGroupDto;

import java.util.List;

public class CreateOrderDto {

    private  List<CreateItemGroupDto> createItemGroupDtoList;

    public CreateOrderDto() {
    }

    public List<CreateItemGroupDto> getCreateItemGroupDtoList() {
        return createItemGroupDtoList;
    }

    public void setCreateItemGroupDtoList(List<CreateItemGroupDto> createItemGroupDtoList) {
        this.createItemGroupDtoList = createItemGroupDtoList;
    }

}
