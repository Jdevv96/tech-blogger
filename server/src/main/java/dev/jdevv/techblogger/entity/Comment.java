package dev.jdevv.techblogger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence"
    )
    @SequenceGenerator(
            name = "comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )
    private Integer commentId;
    private String commentBody;
    private LocalDateTime commentDateAndTime;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "postId")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;
}
