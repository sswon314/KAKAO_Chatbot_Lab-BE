package com.example.goENC.demoCode.dto.book;

import com.example.goENC.demoCode.models.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookAddRequestDto {
    private String title;
    private String author;
    private int price;

    @Builder
    public BookAddRequestDto(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .price(price)
                .build();
    }
}
