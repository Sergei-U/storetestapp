package ru.usov.storetestapp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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


    @OneToOne
    @ApiModelProperty(value = "product category")
    @Column(name = "category_id")
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

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullProduct.class)
    private LocalDateTime creationDate;
}
