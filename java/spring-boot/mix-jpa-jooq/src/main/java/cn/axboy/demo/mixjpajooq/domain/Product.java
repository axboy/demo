package cn.axboy.demo.mixjpajooq.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/12/7 下午9:08
 * 商品表
 */
@Data
@Entity
@Table(name = "demo_product")
public class Product extends AbstractAuditingEntity {

    private String name;

    private Integer inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private Category category;
}
