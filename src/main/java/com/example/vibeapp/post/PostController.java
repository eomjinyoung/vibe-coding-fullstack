package com.example.vibeapp.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(@org.springframework.web.bind.annotation.RequestParam(defaultValue = "1") int page,
            Model model) {
        int size = 5;
        List<Post> posts = postService.findAll(page, size);
        int totalPages = postService.getTotalPages(size);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("hasPrev", page > 1);
        model.addAttribute("hasNext", page < totalPages);
        return "post/posts";
    }

    @GetMapping("/posts/new")
    public String newForm() {
        return "post/post_new_form";
    }

    @GetMapping("/posts/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        Post post = postService.view(no);
        model.addAttribute("post", post);
        return "post/post_detail";
    }

    @GetMapping("/posts/{no}/edit")
    public String editForm(@PathVariable("no") Long no, Model model) {
        Post post = postService.findById(no);
        model.addAttribute("post", post);
        return "post/post_edit_form";
    }

    @GetMapping("/posts/{no}/delete")
    public String delete(@PathVariable("no") Long no) {
        postService.delete(no);
        return "redirect:/posts";
    }

    @org.springframework.web.bind.annotation.PostMapping("/posts/{no}/save")

    public String update(@PathVariable("no") Long no, String title, String content) {
        postService.update(no, title, content);
        return "redirect:/posts/" + no;
    }

    @org.springframework.web.bind.annotation.PostMapping("/posts/add")

    public String create(String title, String content) {
        postService.create(title, content);
        return "redirect:/posts";
    }
}
