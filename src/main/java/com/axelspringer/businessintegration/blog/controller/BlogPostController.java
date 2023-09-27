package com.axelspringer.businessintegration.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.axelspringer.businessintegration.blog.domain.BlogPost;
import com.axelspringer.businessintegration.blog.dto.BlogPostOverview;
import com.axelspringer.businessintegration.blog.service.BlogPostService;

import java.util.Optional;

@Controller
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("/blog/create")
    public String createBlogPost(Model model) {
        model.addAttribute("blogPost", new BlogPost());
        return "blogpost/create";
    }

    @PostMapping("/blog/create")
    public String createBlogPost(@ModelAttribute BlogPost blogPost) {
        blogPostService.createBlogPost(blogPost);
        return "redirect:/";
    }

    @GetMapping("/")
    public String displayAllBlogPosts(Model model) {
        model.addAttribute("posts", blogPostService.getBlogPosts());
        return "blogpost/index";
    }

    @GetMapping("/blog/{blogPostId}")
    public String displayBlogPost(@PathVariable("blogPostId") long blogPostId, Model model) {
        Optional<BlogPostOverview> blogPostOverview = blogPostService.getBlogPost(blogPostId);

        if (blogPostOverview.isPresent()) {
            blogPostService.incrementViews(blogPostId);

            model.addAttribute("post", blogPostOverview.get());
            return "blogpost/blogview";
        }
        return "blogpost/index";
    }
}
