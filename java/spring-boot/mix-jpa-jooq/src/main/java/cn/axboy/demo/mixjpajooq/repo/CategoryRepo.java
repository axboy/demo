package cn.axboy.demo.mixjpajooq.repo;

import cn.axboy.demo.mixjpajooq.domain.Category;
import cn.axboy.demo.mixjpajooq.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
