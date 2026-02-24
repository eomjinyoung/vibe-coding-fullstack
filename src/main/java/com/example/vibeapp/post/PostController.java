package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {
        int size = 5;
        List<PostListDto> posts = postService.findAll(page, size);
        int totalPages = postService.getTotalPages(size);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("hasPrev", page > 1);
        model.addAttribute("hasNext", page < totalPages);
        return "post/posts";
    }

    @GetMapping("/posts/new")
    public String newForm(Model model) {
        model.addAttribute("post", new PostCreateDto(null, null));
        return "post/post_new_form";
    }

    @GetMapping("/posts/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        PostResponseDto post = postService.view(no);
        model.addAttribute("post", post);
        return "post/post_detail";
    }

    @GetMapping("/posts/{no}/edit")
    public String editForm(@PathVariable("no") Long no, Model model) {
        PostResponseDto post = postService.findById(no);
        model.addAttribute("post", post);
        return "post/post_edit_form";
    }

    @GetMapping("/posts/{no}/delete")
    public String delete(@PathVariable("no") Long no) {
        postService.delete(no);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{no}/save")
    public String update(@PathVariable("no") Long no, @Valid @ModelAttribute("post") PostUpdateDto dto,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            PostResponseDto post = postService.findById(no);
            model.addAttribute("post", post); // Keep current view data but show errors?
            // Actually, we should probably merge dto data into the view if we return to
            // edit form.
            return "post/post_edit_form";
        }
        postService.update(no, dto);
        return "redirect:/posts/" + no;
    }

    @PostMapping("/posts/add")
    public String create(@Valid @ModelAttribute("post") PostCreateDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post/post_new_form";
        }
        postService.create(dto);
        return "redirect:/posts";
    }
}
