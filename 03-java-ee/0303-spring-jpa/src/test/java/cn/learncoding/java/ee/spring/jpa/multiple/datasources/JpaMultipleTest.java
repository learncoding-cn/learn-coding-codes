package cn.learncoding.java.ee.spring.jpa.multiple.datasources;


import cn.learncoding.java.ee.spring.jpa.multiple.datasources.config.OrderConfig;
import cn.learncoding.java.ee.spring.jpa.multiple.datasources.config.UserConfig;
import cn.learncoding.java.ee.spring.jpa.multiple.datasources.order.dao.OrderDao;
import cn.learncoding.java.ee.spring.jpa.multiple.datasources.order.model.Order;
import cn.learncoding.java.ee.spring.jpa.multiple.datasources.user.dao.UserDao;
import cn.learncoding.java.ee.spring.jpa.multiple.datasources.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;

/**
 * @author learncoding.cn
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={OrderConfig.class, UserConfig.class})
public class JpaMultipleTest {

    @Resource
    private UserDao userDao;

    @Resource
    private OrderDao orderDao;

    @Test
    public void test(){
        assertNotNull(userDao);
        assertNotNull(orderDao);

        User user = User.builder().userName("learncoding.cn").
                realName("learncoding.cn").password("learncoding.cn").mobilePhone("123").build();
        System.out.println("save \t" + user);
        user = userDao.save(user);
        Long userId = user.getId();

        Order order = Order.builder().productId(1L).productName("书籍").
                price(100).num(2).totalMoney(200L).userId(userId).build();
        System.out.println("save \t" + order);
        order = orderDao.save(order);
        Long orderId = order.getId();

        user = userDao.findById(userId).orElse(null);
        System.out.println("findById \t" + user);

        order = orderDao.findById(orderId).orElse(null);
        System.out.println("findById \t" + order);

        userDao.deleteById(userId);
        System.out.println("deleteById \t" + userId);
        orderDao.deleteById(orderId);
        System.out.println("deleteById \t" + orderId);
    }
}
