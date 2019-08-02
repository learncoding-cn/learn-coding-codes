package cn.learncoding.java.ee.spring.jpa.custom.base;


import cn.learncoding.java.ee.spring.jpa.custom.base.config.Config;
import cn.learncoding.java.ee.spring.jpa.custom.base.dao.UserDao;
import cn.learncoding.java.ee.spring.jpa.custom.base.model.User;
import org.junit.After;
import org.junit.Before;
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
public class JpaCustomBaseTest {

    @Resource
    private UserDao userDao;

    private Long id;

    @Before
    public void before() {
        assertNotNull(userDao);
        User user = User.builder().userName("learncoding.cn").
                realName("learncoding.cn").password("learncoding.cn").mobilePhone("123").build();
        System.out.println("save \t" + user);
        user = userDao.save(user);
        id = user.getId();
    }

    @Test
    public void test(){
        User user = User.builder().id(id).mobilePhone("456").build();
        user = userDao.saveNotNull(user);
        System.out.println("saveNotNull \t" + user);
    }

    @After
    public void after() {
        userDao.deleteAll();
    }
}
