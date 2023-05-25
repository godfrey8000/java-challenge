package jp.co.axa.apidemo.dto;

import lombok.Data;

@Data
public class AuthRequest {

    private String username;
    private String password;

}
