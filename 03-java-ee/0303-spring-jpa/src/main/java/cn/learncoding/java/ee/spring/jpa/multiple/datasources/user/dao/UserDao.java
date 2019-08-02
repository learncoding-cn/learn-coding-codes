package cn.learncoding.java.ee.spring.jpa.multiple.datasources.user.dao;

import cn.learncoding.java.ee.spring.jpa.multiple.datasources.user.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
}
