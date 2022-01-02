package k17.example.readingbook.repository;

import k17.example.readingbook.entity.Liked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikedRepository extends JpaRepository<Liked,Integer> {
      List<Liked> findAllBy();

}
