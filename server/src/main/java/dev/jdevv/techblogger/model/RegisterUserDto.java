package dev.jdevv.techblogger.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {

    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
    @NotEmpty
    private String name;
    @NotEmpty(message = "Please select a role for this user.")
    private String role;
}
