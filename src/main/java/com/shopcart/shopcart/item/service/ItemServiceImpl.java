package com.shopcart.shopcart.item.service;

import com.shopcart.shopcart.item.controller.ItemController;
import com.shopcart.shopcart.item.domain.Image;
import com.shopcart.shopcart.item.domain.Item;
import com.shopcart.shopcart.item.repository.ImageRepository;
import com.shopcart.shopcart.item.repository.ItemRepository;
import com.shopcart.shopcart.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shopcart.shopcart.utils.Constants.*;

@Service
public class ItemServiceImpl implements ItemSerivce {

    private Map<String, Object> map = new HashMap<>();

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StorageService storageService;

    @Override
    public Map<String, Object> saveItem(Item item, List<MultipartFile> attachment) {
        List<Image> imageList = new ArrayList<>();
        map.clear();
        try {
            Item saveItem = itemRepository.save(item);
            logger.info(attachment.toString());
            attachment.forEach(multipartFile -> {

                String ph = multipartFile.getOriginalFilename();
                String name = "Photo_" + System.currentTimeMillis() + "_" + ph;

                logger.info("original file name--->" + multipartFile.getOriginalFilename());
                Image image = new Image();
                image.setImagePath(name);
                image.setSize(multipartFile.getSize());
                image.setType(multipartFile.getContentType());
                storageService.store(multipartFile, name);

                imageList.add(image);
                image.setItem(saveItem);
            });
            imageRepository.saveAll(imageList);
            map.put(MESSAGE, "Items Saved Successfully..");
            return map;
        } catch (Exception e) {
            logger.info(e.getMessage());
            map.put(STATUS, FAIL);
            return map;
        }

    }
}
