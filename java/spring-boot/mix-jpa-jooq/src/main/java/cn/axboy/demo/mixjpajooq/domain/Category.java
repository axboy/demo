package cn.axboy.demo.mixjpajooq.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/12/7 下午9:08
 * 分类
 */
@Data
@Entity
@Table(name = "demo_category")
public class Category extends AbstractAuditingEntity {

    private String title;
}
