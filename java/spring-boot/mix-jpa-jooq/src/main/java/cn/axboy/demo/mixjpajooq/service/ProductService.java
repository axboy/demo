package cn.axboy.demo.mixjpajooq.service;

import cn.axboy.demo.mixjpajooq.domain.Product;
import cn.axboy.demo.mixjpajooq.jooq.tables.records.DemoProductRecord;
import cn.axboy.demo.mixjpajooq.repo.ProductRepo;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static cn.axboy.demo.mixjpajooq.jooq.Tables.*;

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

    @Transactional
    public void jpaUpdate() {
        DemoProductRecord productRecord = dslContext.fetchOne(DEMO_PRODUCT, DEMO_PRODUCT.ID.eq(1L));
        System.out.println("before: " + productRecord.getInventory());

        Product product = productRepo.findOne(1L);
        product.setInventory(product.getInventory() - 1);
        product = productRepo.saveAndFlush(product);        //必须flush
        System.out.println("jpa updated: " + product.getInventory());

        productRecord = dslContext.fetchOne(DEMO_PRODUCT, DEMO_PRODUCT.ID.eq(1L));
        System.out.println("after: " + productRecord.getInventory());
    }

    @Transactional
    public void jooqUpdate() {
        System.out.println("before: " + productRepo.findOne(1L).getInventory());

        dslContext.update(DEMO_PRODUCT)
                .set(DEMO_PRODUCT.INVENTORY, DEMO_PRODUCT.INVENTORY.sub(1))
                .where(DEMO_PRODUCT.ID.eq(1L))
                .execute();
        DemoProductRecord productRecord = dslContext.fetchOne(DEMO_PRODUCT, DEMO_PRODUCT.ID.eq(1L));
        System.out.println("jooq updated: " + productRecord.getInventory());

        System.out.println("after: " + productRepo.findOne(1L).getInventory());
    }
}
