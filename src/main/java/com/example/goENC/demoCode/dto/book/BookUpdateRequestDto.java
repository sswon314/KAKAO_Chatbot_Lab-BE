package com.example.goENC.demoCode.dto.book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookUpdateRequestDto {
    private int newPrice;

    @Builder
    public BookUpdateRequestDto(int newPrice) {
        this.newPrice = newPrice;
    }
}



