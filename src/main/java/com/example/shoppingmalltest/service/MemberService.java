package com.example.shoppingmalltest.service;

import com.example.shoppingmalltest.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member save(Member member);
    Member findById(Long id);
    Optional<Member> findByLoginId(String memberId);
    List<Member> findAll();
}
