package k17.example.readingbook.model.mapper;

import k17.example.readingbook.entity.Post;
import k17.example.readingbook.entity.User;
import k17.example.readingbook.model.dto.UserDto;
import k17.example.readingbook.model.request.ParamCreateUser;
import k17.example.readingbook.model.request.ParamUpdatePost;

public class PostMapper {

    public static Post toParamUpdatePost (ParamUpdatePost p) {
        Post post = new Post();
        post.setPostId(p.getPostId());
        post.setTitle(p.getTitle());
        post.setContent(p.getContent());
        post.setImgPost(p.getImgPost());
        return post;
    }


}
