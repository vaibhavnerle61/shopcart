package com.shopcart.shopcart.item.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shopcart.shopcart.category.domain.Category;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String itemName;

    @Column(length = 250)
    private String itemDescription;

    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Image> imageList;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
/*
{
  "category": {
    "id": 1
  },
  "itemDescription": "product1",
  "itemName": "product-1"
}
 */