package com.example.goENC.demoCode.repositories;

import com.example.goENC.demoCode.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Override
    List<Book> findAll();
}