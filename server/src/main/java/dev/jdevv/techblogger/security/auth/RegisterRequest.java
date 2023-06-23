package dev.jdevv.techblogger.security.auth;

import dev.jdevv.techblogger.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Role role;
}
