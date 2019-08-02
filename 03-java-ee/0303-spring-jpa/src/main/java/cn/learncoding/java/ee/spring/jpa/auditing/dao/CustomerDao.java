package cn.learncoding.java.ee.spring.jpa.auditing.dao;

import cn.learncoding.java.ee.spring.jpa.auditing.model.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<Customer, Long> {

    /**
     *  JPQL 更新
     */
    @Modifying
    @Query("update Customer u set u.name = ?2 where u.name = ?1 ")
    Integer updateNameJPQL(String oldName, String newName);

    /**
     *  SQL 更新
     */
    @Modifying
    @Query(value = "update customer u set u.name = ?2 where u.name = ?1", nativeQuery = true)
    Integer updateNameSQL(String oldName, String newName);
}
