package cn.learncoding.java.ee.spring.jpa.simple;


import cn.learncoding.java.ee.spring.jpa.simple.config.Config;
import cn.learncoding.java.ee.spring.jpa.simple.dao.UserDao;
import cn.learncoding.java.ee.spring.jpa.simple.model.User;
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
@ContextConfiguration(classes=Config.class)
public class JpaSimpleTest {

    @Resource
    private UserDao userDao;

    @Test
    public void test(){
        assertNotNull(userDao);
        User user = User.builder().userName("learncoding.cn").
                realName("learncoding.cn").password("learncoding.cn").mobilePhone("123").build();
        System.out.println("save \t" + user);
        user = userDao.save(user);
        Long id = user.getId();
        user = userDao.findById(id).orElse(null);
        System.out.println("findById \t" + user);
        userDao.deleteById(id);
        System.out.println("deleteById \t" + id);
    }
}
