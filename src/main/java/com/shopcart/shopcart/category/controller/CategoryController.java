package com.shopcart.shopcart.category.controller;

import com.shopcart.shopcart.category.domain.Category;
import com.shopcart.shopcart.category.repositry.CategoryRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shopcart.shopcart.utils.Constants.*;

@RestController
@RequestMapping(value = "/api/category")
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class CategoryController {

    @Autowired
    public CategoryRepo categoryRepo;

    private final Map<String, Object> map = new HashMap<>();

    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @PostMapping(value = "/saveCategory")
    public ResponseEntity<?> saveCategory(@RequestBody Category category) {
        map.clear();
        try {
            categoryRepo.save(category);
            map.put(MESSAGE, "Category saved successfully..");
            map.put(STATUS, SUCCESS);
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            logger.info(e.getMessage());
            map.put(STATUS, FAIL);
            return ResponseEntity.badRequest().body(map);
        }
    }

    @GetMapping(value = "/getCategoryList")
    public ResponseEntity<?> getCategory() {
        map.clear();
        try {
            List<Category> categoryList = categoryRepo.findAll();
            map.put(MESSAGE, "Category list");
            map.put(STATUS, SUCCESS);
            map.put(RESULT, categoryList);
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            logger.info(e.getMessage());
            map.put(STATUS, FAIL);
            return ResponseEntity.badRequest().body(map);
        }
    }
}
