package cn.learncoding.java.ee.spring.jpa.query.method.dao;

import cn.learncoding.java.ee.spring.jpa.query.method.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Long> {


/********************* 查询方法 start *******************************************/
    /**
     * 根据手机号和姓名查询用户
     */
    User findByMobilePhoneAndRealName(String mobilePhone, String name);

    /**
     * 根据姓名模糊查询用户
     */
    List<User> findByRealNameLike(String name);

    /**
     * 根据姓名删除用户
     */
    Long deleteByRealName(String name);

    /**
     * 根据手机号计数
     */
    Long countByMobilePhone(String mobilePhone);

    /**
     * 根据姓名查询依据id正序的第一个用户
     */
    User findTopByRealName(String name);

    /**
     * 根据姓名查询依据id正序的前两个用户
     */
    List<User> findTop2ByRealName(String name);

    /**
     * 根据姓名查询依据id倒序的前两个用户
     */
    List<User> findTop2ByRealNameOrderByIdDesc(String name);

    /**
     * 根据姓名查询依据id正序的第一个用户
     */
    User findFirstByRealName(String name);

    /**
     * 根据姓名查询依据id正序的前两个用户
     */
    List<User> findFirst2ByRealName(String name);

    /**
     * 根据姓名查询依据id倒序的前两个用户
     */
    List<User> findFirst2ByRealNameOrderByIdDesc(String name);

    /**
     * 根据姓名查询依据先id倒序再姓名正序的前两个用户
     */
    List<User> findTop2ByRealNameOrderByIdDescRealNameAsc(String name);

/********************* 查询方法 end *******************************************/

/********************* @Query注解 start *******************************************/
    /**
     *  JPQL 查询
     */
    @Query("select u from User u where u.userName = ?1 ")
    List<User> findByUserNameJPQL(String userName);
    /**
     *  JPQL 更新
     */
    @Modifying
    @Query("update User u set u.userName = ?2 where u.userName = ?1 ")
    Integer updateUserNameJPQL(String oldUserName, String newUserName);
    /**
     *  JPQL 删除
     */
    @Modifying
    @Query("delete from User u  where u.userName = ?1 ")
    Integer deleteUserByUserNameJPQL(String userName);
    /**
     *  SQL 查询
     */
    @Query(value = "select u.* from user u where u.userName = ?1  limit 1", nativeQuery = true)
    User findByUserNameSQL(String userName);
    /**
     *  SQL 更新
     */
    @Modifying
    @Query(value = "update user u set u.userName = ?2 where u.userName = ?1", nativeQuery = true)
    Integer updateUserNameSQL(String oldUserName, String newUserName);
    /**
     *  SQL 删除
     */
    @Modifying
    @Query(value = "delete from user where userName = ?1 ", nativeQuery = true)
    Integer deleteUserByUserNameSQL(String userName);

/********************* @Query注解 start *******************************************/

}
