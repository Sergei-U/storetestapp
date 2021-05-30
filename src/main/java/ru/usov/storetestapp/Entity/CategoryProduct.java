package ru.usov.storetestapp.Entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *
 */
@Data
@Entity
@Table(name = "product_category")
public class CategoryProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "Products_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Products products;

    @JoinColumn(name = "Categores_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
