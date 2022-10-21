package com.jun.board.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostApi {

    private final PostService postService;

    @PostMapping("")
    public void createPost(@RequestBody PostCreateDto postCreateDto) {
        postService.create(postCreateDto);
    }
}
