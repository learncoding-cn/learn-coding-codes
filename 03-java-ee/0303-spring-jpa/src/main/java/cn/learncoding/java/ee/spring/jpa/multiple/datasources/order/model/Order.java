package cn.learncoding.java.ee.spring.jpa.multiple.datasources.order.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="order_info")
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable = false)
    private Long id;

    @Column(name="productId")
    private Long productId;

    @Column(name="productName")
    private String productName;

    @Column(name="num")
    private Integer num;

    @Column(name="price")
    private Integer price;

    @Column(name="totalMoney")
    private Long totalMoney;

    @Column(name="userId")
    private Long userId;
}
