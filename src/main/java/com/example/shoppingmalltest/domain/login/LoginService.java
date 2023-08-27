package com.example.shoppingmalltest.domain.login;


import com.example.shoppingmalltest.domain.member.Member;
import com.example.shoppingmalltest.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final MemberRepository memberRepository;

    public LoginService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Member login(String loginId, String password){
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
