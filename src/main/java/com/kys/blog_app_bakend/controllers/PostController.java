package com.kys.blog_app_bakend.controllers;

import com.kys.blog_app_bakend.payloads.ApiResponse;
import com.kys.blog_app_bakend.payloads.PostDTO;
import com.kys.blog_app_bakend.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(
            @Valid @RequestBody PostDTO postDTO,
            @PathVariable("userId") Integer userId,
            @PathVariable("categoryId") Integer categoryId
    ) {
        PostDTO createdPostDto = this.postService.createPost(postDTO, userId, categoryId);

        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable("postId") Integer postId) {
        PostDTO updatedPostDto = this.postService.updatePost(postDTO, postId);

        return ResponseEntity.ok(updatedPostDto);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId) {
        this.postService.deletePost(postId);

        return new ResponseEntity(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/posts/")
    public ResponseEntity<List<PostDTO>> getAllPost() {
        return ResponseEntity.ok(this.postService.getAllPost());
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("postId") Integer postId) {
        return ResponseEntity.ok(this.postService.getPostById(postId));
    }

//    @GetMapping("/users/{userId}")
//    public  ResponseEntity<PostDTO> getPostByUser(@PathVariable("userId") Integer userId){
//        return ResponseEntity.ok(this.postService.getPostByUser(userId));
//    }

//    @GetMapping({userId}/category/{categoryId}")
//    public  ResponseEntity<PostDTO> getPostByUser(@PathVariable("categoryId") Integer categoryId){
//        return ResponseEntity.ok(this.postService.getPostByCategory(categoryId));
//    }

}
