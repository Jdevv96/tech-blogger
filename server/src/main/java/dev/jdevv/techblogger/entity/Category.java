package dev.jdevv.techblogger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_sequence"
    )
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    private Integer categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Subcategory> subcategories;
    @OneToMany(mappedBy = "category")
    private List<Post> posts;
}
