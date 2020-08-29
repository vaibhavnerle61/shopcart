package com.shopcart.shopcart.item.service;

import com.shopcart.shopcart.item.domain.Item;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ItemSerivce {

    Map<String,Object> saveItem(Item item,List<MultipartFile> attachment);

}
