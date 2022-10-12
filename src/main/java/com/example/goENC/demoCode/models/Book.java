package com.example.goENC.demoCode.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "book")
@NoArgsConstructor
public class Book {
    @Id  // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // SQL에서 auto_increment 의미
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "author", length = 50)
    private String author;

    @Column(name = "price")
    private Integer price;

    @Builder
    // constructor에서 어노테이션으로 @Builder를 주면
    // 나중에 Book객체를 생성시
    // Book obj = Book.builder().title(값).author(값).price(값)으로 생성 가능 (왜 쓰이는지는 잘 모르겠습니다)
    public Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public void update(int newPrice) {
        this.price = newPrice;
    }
}