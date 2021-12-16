package com.example.auth.post;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/")
public class PostController {


    private  final PostService  postService;


    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;

    }

    @GetMapping("posts")
    public List<Post> getPosts() {

        return  postService.getPosts();
    }
    @GetMapping("posts/{id}")
    public Post getPost(@PathVariable Long id){

        return postService.getPost(id);
    }

    @PostMapping("posts")
    public Post addPost(@RequestBody Post post){

        postService.addPost(post);
        return post ;
    }

    @PutMapping("posts/{id}")
    public void updatePost(@PathVariable("id")  Long id, @RequestBody Post post ){
        postService.updatePost(id,post);
    }

    @DeleteMapping("posts/{id}")
    public void   deletePost(@PathVariable("id") Long id){

        postService.deletePost(id);
    }


}
