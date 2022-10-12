package com.example.goENC.demoCode.controllers;

import com.example.goENC.demoCode.dto.book.BookAddRequestDto;
import com.example.goENC.demoCode.dto.book.BookListResponseDto;
import com.example.goENC.demoCode.dto.book.BookUpdateRequestDto;
import com.example.goENC.demoCode.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@ResponseBody
public class BookController {


    @Autowired
    private final BookService bookService;

    // 각각의 API 호출 시 해당하는 service 호출 (BookService.함수명)
    @GetMapping("/book")
    public List<BookListResponseDto> findAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/book/{id}")
    // @PathVariable로 url에서 {id}부분의 값을 매개변수로 받겠다는 의미
    public BookListResponseDto findByIdBook(@PathVariable int id) {
        return bookService.findById(id);
    }

    @PostMapping
    // @RequestBody로 BookAddRequestDto 객체형태의 데이터를 받겠다는 의미
    public Integer addBook(@RequestBody BookAddRequestDto requestDto) {
        return bookService.addBook(requestDto);
    }

    @PutMapping("/{id}")
    public Integer updateBook(@PathVariable int id, @RequestBody BookUpdateRequestDto requestDto) {
        return bookService.updateBook(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Integer deleteBook(@PathVariable int id) {
        return bookService.deleteBook(id);
    }
}
