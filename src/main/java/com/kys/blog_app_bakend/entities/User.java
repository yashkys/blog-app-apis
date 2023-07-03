package com.kys.blog_app_bakend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	@Column(name = "user_name", nullable = false, length  = 100)
	private String name;
	@Column(name = "user_email", nullable = false, length  = 100)
	private String email;
	@Column(name = "user_password", nullable = false, length  = 100)
	private String password;
	@Column(name = "user_about", nullable = false, length  = 100)
	private String about;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // cascade means when parent is removed child is also removed and if parent is added child will be auto saved
	private List<Post> posts = new ArrayList<>();
}
