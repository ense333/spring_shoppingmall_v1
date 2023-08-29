package com.example.shoppingmalltest.repository;

import com.example.shoppingmalltest.domain.member.Member;
import com.example.shoppingmalltest.domain.member.MemberRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemberRepository_v1 implements MemberRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public MemberRepository_v1(DataSource dataSource){
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("member")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Member save(Member member) {
        //String sql = "insert into member(id, memberId, userName) values(?, ?, ?)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(member);

        Number key = jdbcInsert.executeAndReturnKey(param);
        member.setId(key.longValue());
        //template.update(sql, member.getId(), member.getLoginId(), member.getName());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select id, login_id, name, password from member where id = :id";
        //return template.queryForObject(sql, itemRowMapper(), id);
        try{
            Map<String, Object> param = Map.of("id", id);
            Member member = template.queryForObject(sql, param, itemRowMapper());
            return Optional.of(member);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }


    @Override
    public Optional<Member> findByLoginId(String memberId) {
        String sql = "select id, login_id, name, password from member where login_id = :login_id";
        try{
            Map<String, Object> param = Map.of("login_id", memberId);
            Member member = template.queryForObject(sql, param, itemRowMapper());
            return Optional.of(member);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }

        //return Optional.ofNullable(template.queryForObject(sql, itemRowMapper(), memberId));
    }

    @Override
    public List<Member> findAll() {
        String sql = "select id, login_id, name, password from member";
        return template.query(sql, itemRowMapper());
    }

    private RowMapper<Member> itemRowMapper(){
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setLoginId(rs.getString("login_id"));
            member.setName(rs.getString("name"));
            member.setPassword(rs.getString("password"));
            return member;
        };
    }
}
