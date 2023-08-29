package com.example.shoppingmalltest.web.member;

import com.example.shoppingmalltest.domain.member.Member;
import com.example.shoppingmalltest.domain.member.MemberRepository;
import com.example.shoppingmalltest.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member")Member member){
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute Member member, BindingResult result){
        if(result.hasErrors()){
            return "members/addMemberForm";
        }

        memberService.save(member);
        return "redirect:/";
    }

}
