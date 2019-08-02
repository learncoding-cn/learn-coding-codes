package cn.learncoding.java.ee.spring.jpa.custom.base.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable = false)
    private Long id;

    @Column(name="userName")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="mobilePhone")
    private String mobilePhone;

    @Column(name="realName")
    private String realName;
}
