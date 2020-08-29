package com.shopcart.shopcart.item.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Long size;

    private String type;

    private String imagePath;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "item_id")
    private Item item;

}
//    Create Rest Endpoints for
//        ○ List Of Items which will be shown in shopping cart
//        ○ Item will have following attributes
//        ■ Name
//        ■ Description
//        ■ List of Images of the item
//        ○ Image will have
//        ■ Name
//        ■ Size
//        ■ Type
//        ■ Actual Image
//        ○ Every Item must have a category associated with it
//        ➢ For all practical Purpose Apply your intuitions and develop all the rest
//        endpoints related to it.
//        ➢ Integrate the same with Jenkins and Git