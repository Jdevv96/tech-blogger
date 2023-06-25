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
public class Subcategory {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subcategory_sequence"
    )
    @SequenceGenerator(
            name = "subcategory_sequence",
            sequenceName = "subcategory_sequence",
            allocationSize = 1
    )
    private Integer subcategoryId;
    private String subcategoryName;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "subcategory")
    private List<Post> posts;
}
