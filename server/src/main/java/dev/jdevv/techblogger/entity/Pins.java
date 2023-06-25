package dev.jdevv.techblogger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pins {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pins_sequence"
    )
    @SequenceGenerator(
            name = "comment_sequence",
            sequenceName = "pins_sequence",
            allocationSize = 1
    )
    private Integer pinId;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "postId")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;
}
