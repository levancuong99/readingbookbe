package k17.example.readingbook.service;

import k17.example.readingbook.entity.Post;
import k17.example.readingbook.model.request.ParamUpdatePost;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    Post getPostById(int id);
    List<Post> getAllPost();
    void deletePostById(int id);
    Post updatePostById(ParamUpdatePost param,int id);
    Post createPost(ParamUpdatePost param);
}
