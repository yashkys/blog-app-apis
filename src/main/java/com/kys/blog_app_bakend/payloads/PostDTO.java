package com.kys.blog_app_bakend.payloads;

import com.kys.blog_app_bakend.entities.Category;
import com.kys.blog_app_bakend.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;

    private CategoryDTO category;
    private UserDTO user;
}
