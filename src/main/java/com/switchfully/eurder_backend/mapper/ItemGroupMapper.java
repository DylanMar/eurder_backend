package com.switchfully.eurder_backend.mapper;

import com.switchfully.eurder_backend.dto.itemgroupdto.CreateItemGroupDto;
import com.switchfully.eurder_backend.dto.itemgroupdto.ItemGroupDto;
import com.switchfully.eurder_backend.entity.Item;
import com.switchfully.eurder_backend.entity.ItemGroup;
import com.switchfully.eurder_backend.exception.NotFoundException;
import com.switchfully.eurder_backend.repository.ItemRepository;
import com.switchfully.eurder_backend.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class ItemGroupMapper {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    public ItemGroupMapper(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    public ItemGroup mapCreateItemGroupDtoToItemGroup(CreateItemGroupDto createItemGroupDto) {
        Item item = itemRepository.findById(createItemGroupDto.getItemId())
                .orElseThrow(() -> new NotFoundException("Item id not found"));
        return new ItemGroup(item, createItemGroupDto.getAmount());
    }

    public ItemGroupDto mapItemGroupToItemGroupDto(ItemGroup itemGroup) {
        return new ItemGroupDto(
                itemGroup.getItemGroupId(),
                itemGroup.getAmount(),
                itemGroup.getShippingDate(),
                itemGroup.getTotalPrice()
        );
    }

}
