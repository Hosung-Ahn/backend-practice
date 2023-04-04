package helle.restapimysql.controller;

import helle.restapimysql.payload.PostDto;
import helle.restapimysql.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public Result getAllPosts() {
        return new Result(postService.getAllPosts());
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
