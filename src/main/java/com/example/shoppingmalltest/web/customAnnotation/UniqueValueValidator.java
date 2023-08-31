package com.example.shoppingmalltest.web.customAnnotation;

import com.example.shoppingmalltest.domain.member.Member;
import com.example.shoppingmalltest.service.MemberService;
import lombok.RequiredArgsConstructor;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@RequiredArgsConstructor
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String> {

    private final MemberService memberService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<Member> memberList = memberService.findAll();
        for(Member memberVal : memberList){
            if(memberVal.getLoginId().equals(value)){
                return false;
            }
        }
        return true;
    }
}
