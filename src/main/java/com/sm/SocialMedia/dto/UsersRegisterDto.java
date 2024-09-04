package com.sm.SocialMedia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersRegisterDto {

    private String username;
    private String email;
    private String password;
    private String gender;
}
