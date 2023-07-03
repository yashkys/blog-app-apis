package com.kys.blog_app_bakend.repositories;

import com.kys.blog_app_bakend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
