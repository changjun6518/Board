package com.jun.board.post;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostReadDto> findAll() {
        List<Post> postList = postRepository.findAll();
        return postList.stream()
                .map(post -> new PostReadDto(post.getId(), post.getTitle(), post.getDescription()))
                .collect(Collectors.toList());
    }

    public void create(PostCreateDto postCreateDto) {
        Post post = Post.builder()
                .title(postCreateDto.getTitle())
                .description(postCreateDto.getDescription())
                .build();
        postRepository.save(post);
    }


    @Transactional
    public void update(PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(postUpdateDto.getId()).orElseThrow();
        post.setTitle(postUpdateDto.getTitle());
        post.setDescription(postUpdateDto.getDescription());
    }

    public void delete(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        postRepository.delete(post);
    }

    public PostReadDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return new PostReadDto(post.getId(), post.getTitle(), post.getDescription());
    }
}
