package com.example.shoppingmalltest;

import com.example.shoppingmalltest.domain.member.Member;
import com.example.shoppingmalltest.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String homeLoginV1(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,
            required = false)Member loginMember, Model model){
        if(loginMember == null){
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
