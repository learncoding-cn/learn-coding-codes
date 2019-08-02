package cn.learncoding.java.ee.spring.jpa.auditing;


import cn.learncoding.java.ee.spring.jpa.auditing.config.Config;
import cn.learncoding.java.ee.spring.jpa.auditing.dao.CustomerDao;
import cn.learncoding.java.ee.spring.jpa.auditing.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
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
public class JpaAuditingTest {

    @Resource
    private CustomerDao customerDao;

    @Test
    @Rollback(value = false)
    @Transactional
    public void test() throws InterruptedException {
        assertNotNull(customerDao);
        customerDao.deleteAll();
        Customer customer = Customer.builder().name("learncoding.cn").sex("男").mobilePhone("123").build();
        System.out.println("save \t" + customer);
        customer = customerDao.save(customer);
        Long id = customer.getId();
        customer = customerDao.findById(id).orElse(null);
        System.out.println("findById \t" + customer);

        Thread.sleep(1000);

        customer.setMobilePhone("456");
        System.out.println("save \t" + customer);
        customerDao.save(customer);

        customer = customerDao.findById(id).orElse(null);
        System.out.println("findById \t" + customer);

        Thread.sleep(1000);

        Integer updateNum = customerDao.updateNameJPQL("learncoding.cn", "learncoding.cn learncoding.cn");
        System.out.println("updateNameJPQL updateNum: \t" + updateNum);

        customer = customerDao.findById(id).orElse(null);
        System.out.println("findById \t" + customer);

        Thread.sleep(1000);

        updateNum = customerDao.updateNameSQL( "learncoding.cn learncoding.cn", "learncoding.cn");
        System.out.println("updateNameSQL updateNum: \t" + updateNum);

        customer = customerDao.findById(id).orElse(null);
        System.out.println("findById \t" + customer);
    }
}
