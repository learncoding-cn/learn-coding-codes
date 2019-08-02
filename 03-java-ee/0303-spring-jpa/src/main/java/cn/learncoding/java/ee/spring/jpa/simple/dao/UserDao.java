package cn.learncoding.java.ee.spring.jpa.simple.dao;

import cn.learncoding.java.ee.spring.jpa.simple.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
}
