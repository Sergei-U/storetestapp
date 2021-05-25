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
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, insertable = false)
    @ApiModelProperty(value = "category ID")
    private Long id;

    @Column(name = "NAME")
    @ApiModelProperty(value = "category Name")
    private String name;

    @Column(name = "DESCRIPTION")
    @ApiModelProperty(value = "category description")
    private String description;
}
