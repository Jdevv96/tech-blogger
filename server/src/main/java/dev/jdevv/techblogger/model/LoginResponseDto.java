package dev.jdevv.techblogger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class LoginResponseDto {

    private String token;
    private User user;

    @JsonProperty("user")
    User getUser() {
        return user;
    }

    @JsonProperty("token")
    String getToken() {
        return token;
    }


}
