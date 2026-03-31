package ch.samt.blog.data;

import ch.samt.blog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findById(long id);
    List<Post> findByTitleIgnoreCase(String title);
    List<Post> findByAuthorIgnoreCase(String author);
}
