package cn.learncoding.java.ee.spring.jpa.custom.base;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BaseDaoImpl<T> extends SimpleJpaRepository<T, Long> implements BaseDao<T>{

    private final EntityManager entityManager;
    private final JpaEntityInformation<T, Long> entityInformation;

    public BaseDaoImpl(JpaEntityInformation<T, Long> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }

    @Override
    public T saveNotNull(T t) {
        Long id =  entityInformation.getId(t);

        if (id == null){
            return save(t);
        }

        T old = findById(id).orElse(null);
        if (old == null){
            throw new RuntimeException("没有找到对应对象");
        }
        Field[] fields = t.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                //去除静态属性
                if (Modifier.isStatic(field.getModifiers())){
                    continue;
                }

                field.setAccessible(true);
                Object value = field.get(t);
                if (value == null){
                    continue;
                }
                field.set(old, value);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("对象属性存取权限错误", e);
        }
        return save(old);
    }
}
