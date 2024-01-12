package com.switchfully.eurder_backend.repository;

import com.switchfully.eurder_backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


}
