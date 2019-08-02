package cn.learncoding.java.ee.spring.jpa.custom.one;


import cn.learncoding.java.ee.spring.jpa.custom.one.config.Config;
import cn.learncoding.java.ee.spring.jpa.custom.one.dao.UserDao;
import cn.learncoding.java.ee.spring.jpa.custom.one.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;


/**
 * @author learncoding.cn
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Config.class)
public class JpaCustomOneTest {

    @Resource
    private UserDao userDao;

    @Before
    public void before() {
        assertNotNull(userDao);
        User user = User.builder().userName("learncoding.cn").
                realName("learncoding.cn").password("learncoding.cn").mobilePhone("123").build();
        System.out.println("save \t" + user);
        userDao.save(user);
    }

    @Test
    @Transactional
    public void test(){
        Long result = userDao.countByRealNameLike("learncoding");
        System.out.println("countByRealNameLike \t" + result);
    }

    @After
    public void after() {
        userDao.deleteAll();
    }
}
