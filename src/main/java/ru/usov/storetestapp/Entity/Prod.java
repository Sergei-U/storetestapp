package ru.usov.storetestapp.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "PRODUCT")
public class Prod {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, insertable = false)
    @ApiModelProperty(value = "ID product")
    private Long id;


    @OneToMany(mappedBy = "id")
    @ApiModelProperty(value = "product category")
    private Category category;

    @Column(name = "IMG")
    @ApiModelProperty(value = "URL for Image product")
    private String img;

    @Column(name = "NAME")
    @ApiModelProperty(value = "product name")
    private String name;

    @Column(name = "DESCRIPTION")
    @ApiModelProperty(value = "product description")
    private String description;

    @Column(name = "PRICE")
    @ApiModelProperty(value = "product price")
    private int price;

    @Column(name = "QUANTITY")
    @ApiModelProperty(value = "product quantity")
    private int quantity;
}
