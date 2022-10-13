package com.jun.board.post;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostApi {

    private final PostService postService;

    @GetMapping("")
    public ResponseEntity<List<PostReadDto>> getPosts() {
        List<PostReadDto> postReadDtoList = postService.findAll();
        return new ResponseEntity<>(postReadDtoList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Void> updatePost(@RequestBody PostCreateDto postCreateDto) {
        postService.create(postCreateDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<Void> updatePost(@RequestBody PostUpdateDto postUpdateDto) {
        postService.update(postUpdateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deletePost(@RequestParam Long postId) {
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }
}
