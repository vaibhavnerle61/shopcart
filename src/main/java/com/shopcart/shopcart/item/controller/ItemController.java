package com.shopcart.shopcart.item.controller;

import com.shopcart.shopcart.item.domain.Item;
import com.shopcart.shopcart.item.repository.ImageRepository;
import com.shopcart.shopcart.item.repository.ItemRepository;
import com.shopcart.shopcart.item.service.ItemSerivce;
import com.shopcart.shopcart.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shopcart.shopcart.utils.Constants.*;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/item")
public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private final Map<String, Object> map = new HashMap<>();

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ItemSerivce itemSerivce;

    @PostMapping(value = "/saveItems", consumes = {"multipart/form-data"})
    public ResponseEntity<?> saveItems(@RequestPart(value = "item") Item item,
                                       @RequestPart(value = "attachment") List<MultipartFile> attachment) {
        Map<String, Object> map = itemSerivce.saveItem(item, attachment);
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/getItemList")
    public ResponseEntity<?> getItemList(@RequestParam(value = "page") Integer page,
                                         @RequestParam(value = "size") Integer size) {
        map.clear();
        try {
           Pageable pageable = PageRequest.of(page, size);
            map.put(MESSAGE, "List of Items");
            map.put(STATUS, SUCCESS);
            map.put(RESULT, itemRepository.findAll(pageable));
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            logger.info(e.getMessage());
            map.put(MESSAGE, FAIL);
            return ResponseEntity.badRequest().body(map);
        }

    }

}
