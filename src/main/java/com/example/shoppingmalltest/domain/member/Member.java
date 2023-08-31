package com.example.shoppingmalltest.domain.member;

import com.example.shoppingmalltest.web.customAnnotation.UniqueValue;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id;

    @UniqueValue
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
}
