package com.kys.blog_app_bakend.services;

import com.kys.blog_app_bakend.payloads.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId);
    PostDTO updatePost(PostDTO postDto, Integer postId);
    PostDTO getPostById(Integer postId);
    List<PostDTO> getAllPost();
    void deletePost(Integer postId);

    List<PostDTO> getPostByCategory(Integer categoryId);
    List<PostDTO> getPostByUser(Integer userId);

    List<PostDTO> searchPost(String query);

}
