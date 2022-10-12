package com.example.goENC.demoCode.dto.book;

import com.example.goENC.demoCode.models.Book;
import lombok.Getter;

@Getter
public class BookListResponseDto {
    private int id;
    private String title;
    private String author;
    private int price;

    public BookListResponseDto(Book entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.price = entity.getPrice();
    }
}
