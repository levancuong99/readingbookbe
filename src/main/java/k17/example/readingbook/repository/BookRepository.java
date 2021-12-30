package k17.example.readingbook.repository;

import k17.example.readingbook.entity.Book;
import k17.example.readingbook.model.dto.BookDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
      Book findByBookId(int id);
      List<Book> findAllBy();

}
