package cn.learncoding.java.ee.spring.jpa.multiple.datasources.order.dao;

import cn.learncoding.java.ee.spring.jpa.multiple.datasources.order.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderDao extends CrudRepository<Order, Long> {
}
