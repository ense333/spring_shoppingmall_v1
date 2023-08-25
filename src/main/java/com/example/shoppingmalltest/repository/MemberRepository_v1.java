package com.example.shoppingmalltest.repository;

import com.example.shoppingmalltest.domain.item.Item;
import com.example.shoppingmalltest.domain.member.Member;
import com.example.shoppingmalltest.domain.member.MemberRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
        String sql = "insert into member(id, memberId, userName, money, email) values(?, ?, ?, ?, ?)";
        template.update(sql, member.getId(), member.getMemberId(), member.getUserName(), member.getMoney(), member.getEmail());
        return member;
    }

    @Override
    public Member findById(Long id) {
        String sql = "select * from member where id = ?";
        return template.queryForObject(sql, itemRowMapper(), id);
    }


    @Override
    public Optional<Member> findByLoginId(String memberId) {
        String sql = "select * from member where memberId = ?";
        return Optional.ofNullable(template.queryForObject(sql, itemRowMapper(), memberId));
    }

    @Override
    public List<Member> findAll() {

        return null;
    }

    private RowMapper<Member> itemRowMapper(){
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setMemberId(rs.getString("memberId"));
            member.setUserName(rs.getString("userName"));
            member.setEmail(rs.getString("email"));
            member.setMoney(rs.getInt("money"));
            return member;
        };
    }
}
