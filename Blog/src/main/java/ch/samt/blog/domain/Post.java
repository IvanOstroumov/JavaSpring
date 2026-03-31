package ch.samt.blog.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    @SequenceGenerator(name = "post_seq", sequenceName = "post_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 5, max = 200)
    private String title;

    @NotNull
    private Date pubdate;

    @NotBlank
    @Size(min = 5, max = 50)
    private String category;

    @NotBlank
    @Size(min = 5, max = 50)
    private String author;

    @NotNull
    private int likes;

    @Lob
    private String content;

}
