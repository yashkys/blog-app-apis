package com.kys.blog_app_bakend.services.impl;

import com.kys.blog_app_bakend.entities.Category;
import com.kys.blog_app_bakend.entities.Post;
import com.kys.blog_app_bakend.entities.User;
import com.kys.blog_app_bakend.exceptions.ResourceNotFoundException;
import com.kys.blog_app_bakend.payloads.PostDTO;
import com.kys.blog_app_bakend.repositories.CategoryRepository;
import com.kys.blog_app_bakend.repositories.PostRepository;
import com.kys.blog_app_bakend.repositories.UserRepository;
import com.kys.blog_app_bakend.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImage("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = this.postRepository.save(post);
        return this.modelMapper.map(savedPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDto, Integer postId) {
        Post post = this.modelMapper.map(postDto,Post.class);
        Post updatedPost = this.postRepository.save(post);
        return this.modelMapper.map(updatedPost, PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "Id", postId));
        return this.modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllPost() {
        List<Post> posts = this.postRepository.findAll();
        List<PostDTO> postDTOS = posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "Id", postId));
        this.postRepository.delete(post);
    }

    @Override
    public List<PostDTO> getPostByCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        List<Post> posts = this.postRepository.findByCategory(category);
        List<PostDTO> postDTOS = posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> getPostByUser(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        List<Post> posts = this.postRepository.findByUser(user);
        List<PostDTO> postDTOS = posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> searchPost(String query) {
        return null;
    }
}
