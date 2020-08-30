package com.shopcart.shopcart.item.repository;

import com.shopcart.shopcart.item.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends PagingAndSortingRepository<Image,Integer> {
}
