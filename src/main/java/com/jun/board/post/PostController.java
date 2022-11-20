package com.jun.board.post;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("")
    public ModelAndView getPosts() {
        ModelAndView modelAndView = new ModelAndView();
        List<PostReadDto> postReadDtoList = postService.findAll();
        modelAndView.addObject("postList", postReadDtoList);
        modelAndView.setViewName("post/main");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getPost(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        PostReadDto postReadDto = postService.getPostById(id);
        modelAndView.addObject("post", postReadDto);
        modelAndView.setViewName("post/modify");
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView createPost(PostCreateDto postCreateDto) {
        postService.create(postCreateDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/post");
        return modelAndView;
    }

    @PutMapping("")
    public ModelAndView updatePost(PostUpdateDto postUpdateDto) {
        postService.update(postUpdateDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/post");
        return modelAndView;
    }

    @DeleteMapping("")
    public ModelAndView deletePost(Long id) {
        postService.delete(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/post");
        return modelAndView;
    }
}
