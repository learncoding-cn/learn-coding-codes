package cn.learncoding.java.ee.spring.jpa.auditing.model;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
@Table(name="customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="mobilePhone")
    private String mobilePhone;

    @Column(name="sex")
    private String sex;

    @CreatedDate
    @Column(name="createTime")
    private Date createTime;

    @CreatedBy
    @Column(name="createUserId")
    private  Long createUserId;

    @LastModifiedDate
    @Column(name="updateTime")
    private  Date updateTime;

    @LastModifiedBy
    @Column(name="updateUserId")
    private  Long updateUserId;
}
