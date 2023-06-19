package dev.jdevv.techblogger.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDto {

    private String userName;
    private String password;
}
