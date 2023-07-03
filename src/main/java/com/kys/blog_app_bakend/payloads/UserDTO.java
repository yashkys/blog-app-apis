package com.kys.blog_app_bakend.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO { //data transfer object

    private int id;
    @NotEmpty
    @Size(min=4, message = "Username must be min of 4 characters")
    private String name;
    @Email(message = "Email address is not valid")
    private String email;
    @NotEmpty
    @Size(min = 8 , max = 16, message = "Password must be of min 8 characters and max of 10 characters")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$")
    private String password;
    @NotEmpty
    private String about;

}
