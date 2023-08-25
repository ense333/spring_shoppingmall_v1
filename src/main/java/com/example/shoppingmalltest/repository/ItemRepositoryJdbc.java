package com.example.shoppingmalltest.repository;

import com.example.shoppingmalltest.domain.item.Item;
import com.example.shoppingmalltest.domain.item.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
public class ItemRepositoryJdbc implements ItemRepository {

    private final JdbcTemplate template;

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
    public Item findById(Long itemId) {
        String sql = "select * from item where id = ?";
        return template.queryForObject(sql, itemRowMapper(), itemId);
    }

    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public void update(Long itemId, Item updateParam) {
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
