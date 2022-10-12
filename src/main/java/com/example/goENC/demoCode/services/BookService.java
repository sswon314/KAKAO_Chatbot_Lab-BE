package com.example.goENC.demoCode.services;

import com.example.goENC.demoCode.dto.book.BookAddRequestDto;
import com.example.goENC.demoCode.dto.book.BookListResponseDto;
import com.example.goENC.demoCode.dto.book.BookUpdateRequestDto;
import com.example.goENC.demoCode.models.Book;
import com.example.goENC.demoCode.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 constructor 생성
@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Transactional
    // 책의 정보를 BookAddRequestDto객체 형태로 받은 후 DB에 저장하고 해당 id값을 반환
    public Integer addBook(BookAddRequestDto requestDto) {
        return bookRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    // 매개변수로 받은 id값으로 DB에서 알맞는 book을 찾은 후
    // 매개변수로 받은 BookUpdateRequestDto객체로 업데이트한다 그후 성공 시 해당 id값을 반환
    public Integer updateBook(int id, BookUpdateRequestDto requestDto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 책이 없습니다. id=" + id));
        book.update(requestDto.getNewPrice());

        return id;
    }

    @Transactional(readOnly = true) // 트랜젝션을 읽기 전용으로 함
    public List<BookListResponseDto> findAll() {
        return bookRepository.findAll().stream()
                .map(BookListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookListResponseDto findById(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 책이 없습니다. id=" + id));
        return new BookListResponseDto(book);
    }

    @Transactional
    public Integer deleteBook(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 책이 없습니다. id=" + id));
        bookRepository.delete(book);

        return id;
    }
}
