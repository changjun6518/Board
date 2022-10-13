package com.jun.board.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateDto {

    private Long id;
    private String title;
    private String description;
}
