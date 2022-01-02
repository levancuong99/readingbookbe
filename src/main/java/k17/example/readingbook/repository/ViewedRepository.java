package k17.example.readingbook.repository;

import k17.example.readingbook.entity.Viewed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewedRepository extends JpaRepository<Viewed,Integer> {
      List<Viewed> findAllBy();

}
