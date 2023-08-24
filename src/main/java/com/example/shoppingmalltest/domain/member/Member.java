package com.example.shoppingmalltest.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id;
    @NotEmpty
    private String memberId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String email;
    private Integer money;
}
