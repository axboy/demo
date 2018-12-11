package cn.axboy.demo.mixdb.mapper;

import org.apache.ibatis.annotations.*;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/12/11 下午5:37
 * porduct mapper
 */
@Mapper
public interface ProductMapper {

    @Insert("insert into demo_product(name, inventory, category_id) values(#{name}, #{inventory}, #{category_id})")
    int addOne(@Param("name") String name, @Param("inventory") int inventory, @Param("category_id") long category_id);

    @Update("update demo_product set inventory = inventory - #{num} where id = #{id} and inventory > #{num}")
    int subInventory(@Param("id") long id, @Param("num") int num);

    @Select("select inventory from demo_product where id = #{id}")
    Integer getInventoryById(@Param("id") long id);
}
