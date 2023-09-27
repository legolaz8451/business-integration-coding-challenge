package com.axelspringer.businessintegration.blog.service;

import org.springframework.stereotype.Service;
import com.axelspringer.businessintegration.blog.domain.BlogPost;
import com.axelspringer.businessintegration.blog.domain.User;
import com.axelspringer.businessintegration.blog.dto.BlogPostOverview;
import com.axelspringer.businessintegration.blog.repository.BlogPostRepository;
import com.axelspringer.businessintegration.blog.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    private final UserRepository userRepository;

    private final AuthenticationService authenticationService;

    public BlogPostService(BlogPostRepository blogPostRepository, UserRepository userRepository, AuthenticationService authenticationService) {
        this.blogPostRepository = blogPostRepository;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    public void createBlogPost(BlogPost blogPost) {
        String username = authenticationService.getUsername();
        User user = userRepository.findByUsername(username);
        blogPost.setAuthor(user);
        blogPost.setViews(0L);
        blogPostRepository.save(blogPost);
    }

    public List<BlogPostOverview> getBlogPosts() {
        List<BlogPost> blogPosts = blogPostRepository.findAll();

        return blogPosts.stream()
                .map(BlogPostOverview::new)
                .collect(Collectors.toList());
    }


    public Optional<BlogPostOverview> getBlogPost(long id) {
        Optional<BlogPost> blogPost = blogPostRepository.findById(id);

        return blogPost.map(BlogPostOverview::new);
    }

    public void incrementViews(long id) {
        Optional<BlogPost> blogPost = blogPostRepository.findById(id);
        if (blogPost.isPresent()) {
            BlogPost post = blogPost.get();
            post.setViews(post.getViews() + 1);
            blogPostRepository.save(post);
        }
    }
}
