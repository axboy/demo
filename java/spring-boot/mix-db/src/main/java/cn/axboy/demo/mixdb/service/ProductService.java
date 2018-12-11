package cn.axboy.demo.mixdb.service;

import cn.axboy.demo.mixdb.domain.Product;
import cn.axboy.demo.mixdb.mapper.ProductMapper;
import cn.axboy.demo.mixdb.repo.ProductRepo;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static cn.axboy.demo.mixdb.jooq.Tables.*;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/12/8 上午10:19
 */
@Service
public class ProductService {

    @Autowired
    private DSLContext dslContext;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public void jpaUpdate() {
        print(1L, "jpa update before");

        Product product = productRepo.findOne(1L);
        product.setInventory(product.getInventory() - 1);
        productRepo.saveAndFlush(product);

        print(1L, "jpa update after");
    }

    @Transactional
    public void jooqUpdate() {

        print(1L, "jooq update before");

        dslContext.update(DEMO_PRODUCT)
                .set(DEMO_PRODUCT.INVENTORY, DEMO_PRODUCT.INVENTORY.sub(1))
                .where(DEMO_PRODUCT.ID.eq(1L))
                .execute();

        print(1L, "jooq update after");
    }

    @Transactional
    public void mybatisUpdate() {
        print(1L, "mybatis update before");
        productMapper.subInventory(1L, 1);
        print(1L, "mybatis update after");
    }

    public void print(long id, String msg) {
        System.out.println("===> " + msg);
        System.out.println(String.format("jpa: %d", productRepo.findOne(1L).getInventory()));
        System.out.println(String.format("jooq: %d", dslContext.fetchOne(DEMO_PRODUCT, DEMO_PRODUCT.ID.eq(1L)).getInventory()));
        System.out.println(String.format("mybatis: %d", productMapper.getInventoryById(1L)));
    }
}
