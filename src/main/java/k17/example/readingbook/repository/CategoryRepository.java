package k17.example.readingbook.repository;

import k17.example.readingbook.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findByCateId(int id);
    List<Category> findAllBy();
}
