package k17.example.readingbook.service;

import k17.example.readingbook.entity.Post;
import k17.example.readingbook.model.mapper.PostMapper;
import k17.example.readingbook.model.request.ParamUpdatePost;
import k17.example.readingbook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post getPostById(int id) {
        Post post=postRepository.findByPostId(id);
        return post;
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> posts=postRepository.findAllBy();
        return posts;
    }

    @Override
    public void deletePostById(int id) {
        Post post=postRepository.findByPostId(id);
        postRepository.delete(post);
    }

    @Override
    public Post updatePostById(ParamUpdatePost p, int id) {
        Post post=postRepository.findByPostId(id);
        post.setPostId(p.getPostId());
        post.setTitle(p.getTitle());
        post.setContent(p.getContent());
        post.setImgPost(p.getImgPost());
        postRepository.save(post);
        return post;
    }

    @Override
    public Post createPost(ParamUpdatePost param) {
        Post post=PostMapper.toParamUpdatePost(param);
        postRepository.save(post);
        return post;
    }
}
