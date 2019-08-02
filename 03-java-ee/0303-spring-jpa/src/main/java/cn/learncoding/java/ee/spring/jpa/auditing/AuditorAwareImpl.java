package cn.learncoding.java.ee.spring.jpa.auditing;


import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        /**
         *  TODO 根据自身架构选型，放入当前操作人信息
         */
        return Optional.of(1L);
    }
}
