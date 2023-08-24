package com.example.shoppingmalltest.repository;

import com.example.shoppingmalltest.domain.member.Member;
import com.example.shoppingmalltest.domain.member.MemberRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class MemberRepository_v1 implements MemberRepository {

    private final JdbcTemplate template;

    public MemberRepository_v1(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        return null;
    }

    @Override
    public Member findById(Long id) {
        return null;
    }

    @Override
    public void update(String memberId, int money) {

    }

    @Override
    public void delete(String memberId) {

    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }

    @Override
    public void clearStore() {

    }
}
