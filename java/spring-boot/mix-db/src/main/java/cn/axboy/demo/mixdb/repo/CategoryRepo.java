package cn.axboy.demo.mixdb.repo;

import cn.axboy.demo.mixdb.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
