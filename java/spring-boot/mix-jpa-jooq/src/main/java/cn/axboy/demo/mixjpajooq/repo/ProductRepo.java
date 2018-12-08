package cn.axboy.demo.mixjpajooq.repo;

import cn.axboy.demo.mixjpajooq.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
