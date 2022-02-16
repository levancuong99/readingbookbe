package k17.example.readingbook.service;

import k17.example.readingbook.entity.Post;
import k17.example.readingbook.entity.Proposal;
import k17.example.readingbook.model.dto.PostPagingDto;
import k17.example.readingbook.model.dto.PropPagingDto;
import k17.example.readingbook.model.mapper.PostMapper;
import k17.example.readingbook.model.request.ParamUpdatePost;
import k17.example.readingbook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        post.setCreatedAt(new Date());
        postRepository.save(post);
        return post;
    }

    @Override
    public Post createPost(ParamUpdatePost param) {
        Post post=PostMapper.toParamUpdatePost(param);
        post.setCreatedAt(new Date());
        postRepository.save(post);
        return post;
    }
    int numberRowPerPage=5;
    @Override
    public PostPagingDto getAllPostPaging(int pageNumber) {

        List<Post> postList=postRepository.findAllBy();
        int numberAllRow=postList.size();
        int totalPage=numberAllRow/numberRowPerPage+1;
        PostPagingDto postPagingDto=new PostPagingDto();
        postPagingDto.setCurrentPage(pageNumber);
        postPagingDto.setTotalPage(totalPage);
        postPagingDto.setAllRow(numberAllRow);

        int endIndex=0;
        int startIndex=(pageNumber-1)*numberRowPerPage;
        if(startIndex+numberRowPerPage<=numberAllRow) {
            endIndex=startIndex+numberRowPerPage;
        }else {
            endIndex=numberAllRow;
        }
        List<Post> posts=new ArrayList<>();
        for(int i=startIndex; i<endIndex;i++) {
            posts.add(postList.get(i));
        }
        postPagingDto.setPosts(posts);
        postPagingDto.setNumberRowCurrentpage(posts.size());
        return postPagingDto;
    }
}
