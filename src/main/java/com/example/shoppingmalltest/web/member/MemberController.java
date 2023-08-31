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

import java.util.List;

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

        //여기다 이미 존재하는 아이디가 있는 경우 검증 로직 추가하기
        /*
        List<Member> memberList = memberService.findAll();
        for(Member memberVal : memberList){
            if(memberVal.getLoginId().equals(member.getLoginId())){
                result.reject("sameLoginId", null);
            }
        }*/

        if(result.hasErrors()){
            return "members/addMemberForm";
        }

        memberService.save(member);
        return "redirect:/";
    }

}
