package cn.axboy.demo.mixdb;

import cn.axboy.demo.mixdb.domain.Category;
import cn.axboy.demo.mixdb.domain.Product;
import cn.axboy.demo.mixdb.jooq.tables.records.DemoProductRecord;
import cn.axboy.demo.mixdb.mapper.ProductMapper;
import cn.axboy.demo.mixdb.repo.CategoryRepo;
import cn.axboy.demo.mixdb.repo.ProductRepo;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static cn.axboy.demo.mixdb.jooq.Tables.*;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/12/7 下午9:41
 * test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private DSLContext dslContext;

    @Test
    public void jpaCreate() {
        Category cat1 = new Category();
        cat1.setTitle("手机");
        categoryRepo.save(cat1);

        Product p1 = new Product();
        p1.setName("iPhone 7");
        p1.setInventory(100);
        p1.setCategory(cat1);
        productRepo.save(p1);

        Product p2 = new Product();
        p2.setName("iPhone 8");
        p2.setInventory(100);
        p2.setCategory(cat1);
        productRepo.save(p2);
    }

    @Test
    public void jooqCreate() {
        DemoProductRecord productRecord = dslContext.newRecord(DEMO_PRODUCT);
        productRecord.setName("小米6");
        productRecord.setInventory(200);
        productRecord.setCategoryId(1L);
        productRecord.store();

        dslContext.insertInto(DEMO_PRODUCT)
                .set(DEMO_PRODUCT.NAME, "mi 8")
                .set(DEMO_PRODUCT.INVENTORY, 10)
                .set(DEMO_PRODUCT.CATEGORY_ID, 1L)
                .execute();
    }

    @Test
    public void mybatisCreate() {
        productMapper.addOne("三星s8", 300, 1L);
    }
}
