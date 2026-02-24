package com.example.vibeapp.controller;

import com.example.vibeapp.domain.Post;
import com.example.vibeapp.service.PostService;
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
    public String list(Model model) {
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        Post post = postService.viewPost(no);
        model.addAttribute("post", post);
        return "post_detail";
    }

    @GetMapping("/posts/{no}/edit")
    public String editForm(@PathVariable("no") Long no, Model model) {
        Post post = postService.getPost(no);
        model.addAttribute("post", post);
        return "post_edit_form";
    }

    @org.springframework.web.bind.annotation.PostMapping("/posts/{no}/save")
    public String save(@PathVariable("no") Long no, String title, String content) {
        postService.updatePost(no, title, content);
        return "redirect:/posts/" + no;
    }

    @org.springframework.web.bind.annotation.PostMapping("/posts/add")

    public String add(String title, String content) {
        postService.registerPost(title, content);
        return "redirect:/posts";
    }
}
