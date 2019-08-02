package cn.learncoding.java.ee.spring.jpa.custom.base;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseDao<T> extends CrudRepository<T, Long> {

    T saveNotNull(T t);
}
