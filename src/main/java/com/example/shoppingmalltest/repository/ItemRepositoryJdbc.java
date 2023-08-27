package com.example.shoppingmalltest.repository;

import com.example.shoppingmalltest.domain.item.Item;
import com.example.shoppingmalltest.domain.item.ItemRepository;
import com.example.shoppingmalltest.web.item.form.ItemUpdateForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class ItemRepositoryJdbc implements ItemRepository {

    private final JdbcTemplate template;
    //private final NamedParameterJdbcTemplate template;

    public ItemRepositoryJdbc(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Item save(Item item) {
        String sql = "insert into item(id, itemName, price) values(?, ?, ?)";
        template.update(sql, item.getId(), item.getItemName(), item.getItemPrice());
        return item;
    }

    @Override
    public Optional<Item> findById(Long itemId) {
        String sql = "select * from item where id = ?";
        return Optional.ofNullable(template.queryForObject(sql, itemRowMapper(), itemId));
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();
        String sql = "select id, item_name, price, quantity from item";
        //동적 쿼리
        if (StringUtils.hasText(itemName) || maxPrice != null) {
            sql += " where";
        }
        boolean andFlag = false;
        List<Object> param = new ArrayList<>();
        if (StringUtils.hasText(itemName)) {
            sql += " item_name like concat('%',?,'%')";
            param.add(itemName);
            andFlag = true;
        }
        if (maxPrice != null) {
            if (andFlag) {
                sql += " and";
            }
            sql += " price <= ?";
            param.add(maxPrice);
        }
        log.info("sql={}", sql);
        return template.query(sql, itemRowMapper(), param.toArray());
    }

    @Override
    public void update(Long itemId, ItemUpdateForm updateParam) {
        String sql = "update item set price=? where itemName=?";
        template.update(sql, updateParam.getItemPrice(), updateParam.getItemName());
    }

    @Override
    public void clearStore() {
    }

    private RowMapper<Item> itemRowMapper(){
        return (rs, rowNum) -> {
            Item item = new Item();
            item.setId(rs.getLong("id"));
            item.setItemName(rs.getString("itemName"));
            item.setItemPrice(rs.getInt("price"));
            return item;
        };
    }
}
