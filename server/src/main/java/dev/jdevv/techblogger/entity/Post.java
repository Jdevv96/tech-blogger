package dev.jdevv.techblogger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    private Integer postId;
    private String title;
    private LocalDateTime publishDateAndTime;
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "categoryId")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "subcategory_id", referencedColumnName = "subcategoryId")
    private Subcategory subcategory;
    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "imageId")
    private Image image;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
    @OneToMany(mappedBy = "post")
    private List<Pins> pinsList;
}
