package cn.axboy.demo.mixdb.repo;

import cn.axboy.demo.mixdb.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
