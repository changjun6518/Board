package com.jun.board.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostReadDto {

    private Long id;
    private String title;
    private String description;
}
