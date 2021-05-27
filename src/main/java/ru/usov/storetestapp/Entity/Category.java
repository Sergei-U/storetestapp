package ru.usov.storetestapp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullCategory.class)
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prod> prodList;
}
