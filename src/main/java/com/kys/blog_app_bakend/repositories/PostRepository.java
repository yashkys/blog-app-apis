package com.kys.blog_app_bakend.repositories;

import com.kys.blog_app_bakend.entities.Category;
import com.kys.blog_app_bakend.entities.Post;
import com.kys.blog_app_bakend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository  extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

}
