package cn.learncoding.java.ee.spring.jpa.custom.one.dao;

import cn.learncoding.java.ee.spring.jpa.custom.one.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long>,UserDaoExtra {
}
