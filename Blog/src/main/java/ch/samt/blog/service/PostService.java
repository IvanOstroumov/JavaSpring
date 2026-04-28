package ch.samt.blog.service;

import ch.samt.blog.data.PostRepository;
import ch.samt.blog.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public List<Post> findByAuthor(String author) {
        return postRepository.findByAuthorIgnoreCase(author);
    }
    public Post findById(Long id) {
        return postRepository.findById(id).get();
    }
    public List<Post> getBest(){
        List<Post> posts = postRepository.findAll();
        posts.sort(Comparator.comparing(Post::getLikes));
        return posts.subList(0, 2);
    }
}