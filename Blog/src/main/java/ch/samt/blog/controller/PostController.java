package ch.samt.blog.controller;

import ch.samt.blog.domain.Post;
import ch.samt.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/blog")
@Controller
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String loadPosts(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "postList";
    }

    @GetMapping("/{authorToFilter}")
    public String loadPostByAuthor(Model model, @PathVariable String authorToFilter) {
        List<Post> filteredPosts = postService.findByAuthor(authorToFilter);
        if (filteredPosts.isEmpty()) {
            return "error";
        }
        model.addAttribute("posts", filteredPosts);
        return "postList";
    }

    @GetMapping("/post")
    public String loadPostById(Model model, @RequestParam(value = "postid", required = true) Long id) {
        if (postService.findById(id) == null) {
            return "error";
        }
        model.addAttribute("posts", postService.findById(id));
        return "postList";
    }

    @GetMapping("/best")
    public String loadBestPosts(Model model) {
        model.addAttribute("posts", postService.getBest());
        return "postList";
    }

    @GetMapping("/new")
    public String loadInsertPage(@ModelAttribute Post Post) {
        return "insertPost";
    }

    @PostMapping("/new")
    public String savePosts(@Valid Post Post, Errors errors) {
        if (errors.hasErrors()) {
            return "insertPost";
        }
        postService.save(Post);
        return "redirect:/Blog";
    }
}
