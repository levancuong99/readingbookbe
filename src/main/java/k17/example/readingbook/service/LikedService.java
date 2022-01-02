package k17.example.readingbook.service;

import k17.example.readingbook.model.dto.BookPagingDto;
import org.springframework.stereotype.Service;

@Service
public interface LikedService {
    BookPagingDto getAllBookLikedByUser(int userId,int pageNumber);
}
