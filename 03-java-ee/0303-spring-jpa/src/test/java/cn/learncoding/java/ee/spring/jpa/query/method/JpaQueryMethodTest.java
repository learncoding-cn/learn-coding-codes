package cn.learncoding.java.ee.spring.jpa.query.method;


import cn.learncoding.java.ee.spring.jpa.query.method.config.Config;
import cn.learncoding.java.ee.spring.jpa.query.method.dao.UserDao;
import cn.learncoding.java.ee.spring.jpa.query.method.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author learncoding.cn
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Config.class)
public class JpaQueryMethodTest {

    @Resource
    private UserDao userDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setup()
    {
        Long num = userDao.deleteByRealName("learncoding.cn");
        User user = User.builder().userName("learncoding.cn").
                realName("learncoding.cn").password("learncoding.cn").mobilePhone("123").build();
        userDao.save(user);
        for (int i = 0; i < 5; i++) {
            User user1 = new User();
            BeanUtils.copyProperties(user, user1);
            user1.setId(null);
            userDao.save(user1);
        }
        entityManager.flush();
        entityManager.clear();
    }


    @Test
    @Rollback(value = false)
    @Transactional
    public void testQueryMethod(){
        assertNotNull(userDao);
        Long num = userDao.countByMobilePhone("123");
        System.out.println("total num \t" + num);

        User firstUser =  userDao.findFirstByRealName("learncoding.cn");
        System.out.println("firstUser \t" + firstUser);
        User topUser = userDao.findTopByRealName("learncoding.cn");
        System.out.println("topUser \t" + topUser);

        List<User> first2UserList = userDao.findFirst2ByRealName("learncoding.cn");
        System.out.println("first2UserList \t" + first2UserList);
        List<User> top2UserList = userDao.findTop2ByRealName("learncoding.cn");
        System.out.println("top2UserList \t" + top2UserList);

        List<User> first2OrderUserList = userDao.findFirst2ByRealNameOrderByIdDesc("learncoding.cn");
        System.out.println("first2OrderUserList \t" + first2OrderUserList);
        List<User> top2OrderUserList = userDao.findTop2ByRealNameOrderByIdDesc("learncoding.cn");
        System.out.println("top2OrderUserList \t" + top2OrderUserList);

        List<User> top2Order2UserList =  userDao.findTop2ByRealNameOrderByIdDescRealNameAsc("learncoding.cn");
        System.out.println("top2Order2UserList \t" + top2Order2UserList);
    }

    @Test
    @Rollback(value = false)
    @Transactional
    public void testQueryAnnotationJPQL(){
        assertNotNull(userDao);
        Long num = userDao.countByMobilePhone("123");
        System.out.println("total num \t" + num);

        List<User> list = userDao.findByUserNameJPQL("learncoding.cn");
        System.out.println("findByUserNameJPQL \t" + list);

        Integer updateNum = userDao.updateUserNameJPQL("learncoding.cn", "learncoding.cn learncoding.cn");
        System.out.println("updateUserNameJPQL updateNum: \t" + updateNum);

        Integer deleteNum = userDao.deleteUserByUserNameJPQL("learncoding.cn learncoding.cn");
        System.out.println("deleteUserByUserNameJPQL deleteNum: \t" + deleteNum);
    }

    @Test
    @Rollback(value = false)
    @Transactional
    public void testQueryAnnotationSQL(){
        assertNotNull(userDao);
        Long num = userDao.countByMobilePhone("123");
        System.out.println("total num \t" + num);

        User user = userDao.findByUserNameSQL("learncoding.cn");
        System.out.println("findByUserNameJPQL \t" + user);

        Integer updateNum = userDao.updateUserNameSQL("learncoding.cn", "learncoding.cn learncoding.cn");
        System.out.println("updateUserNameJPQL updateNum: \t" + updateNum);

        Integer deleteNum = userDao.deleteUserByUserNameSQL("learncoding.cn learncoding.cn");
        System.out.println("deleteUserByUserNameJPQL deleteNum: \t" + deleteNum);
    }
}
