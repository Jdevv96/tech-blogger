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
public class Image {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "image_sequence"
    )
    @SequenceGenerator(
            name = "image_sequence",
            sequenceName = "image_sequence",
            allocationSize = 1
    )
    private Integer imageId;
    private String description;

    @OneToMany(mappedBy = "image")
    private List<Post> posts;
}
