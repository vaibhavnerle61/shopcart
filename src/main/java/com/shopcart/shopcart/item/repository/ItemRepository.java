package com.shopcart.shopcart.item.repository;

import com.shopcart.shopcart.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item,Integer> {
}
