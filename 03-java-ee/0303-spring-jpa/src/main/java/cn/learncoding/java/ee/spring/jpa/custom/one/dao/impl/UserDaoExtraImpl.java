package cn.learncoding.java.ee.spring.jpa.custom.one.dao.impl;

import cn.learncoding.java.ee.spring.jpa.custom.one.dao.UserDaoExtra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("userDaoExtraImpl")
public class UserDaoExtraImpl implements UserDaoExtra {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long countByRealNameLike(String realName) {
        return jdbcTemplate.queryForObject("select count(1) from user where realName like ?", Long.class, "%" + realName + "%");
    }
}
