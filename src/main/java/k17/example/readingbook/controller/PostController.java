package k17.example.readingbook.controller;


import k17.example.readingbook.entity.Post;
import k17.example.readingbook.model.request.*;
import k17.example.readingbook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class PostController
{

    @Autowired
    private PostService postService;

    @GetMapping(value="/posts")
    public ResponseEntity<?> getListPost() {
        List<Post> posts = postService.getAllPost();
        return ResponseEntity.ok(posts);
    }

    @GetMapping(value="post/{id}")
    public ResponseEntity<?> getPostById(@PathVariable int id) {
        Post post =  postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable int id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("delete success");
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<?> updatePostById(@Validated @RequestBody ParamUpdatePost req, @PathVariable int id) {
        Post post = postService.updatePostById(req,id);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/post/create")
    public ResponseEntity<?> createPost(@Validated @RequestBody ParamUpdatePost req) {
        Post post = postService.createPost(req);
        return ResponseEntity.ok(post);
    }


}
