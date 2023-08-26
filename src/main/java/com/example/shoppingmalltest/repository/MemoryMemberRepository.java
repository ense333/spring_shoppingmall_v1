package com.example.shoppingmalltest.repository;

import com.example.shoppingmalltest.domain.member.Member;
import com.example.shoppingmalltest.domain.member.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;



    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public Optional<Member> findByLoginId(String memberId) {
        return findAll().stream()
                .filter(m -> m.getMemberId().equals(memberId))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
