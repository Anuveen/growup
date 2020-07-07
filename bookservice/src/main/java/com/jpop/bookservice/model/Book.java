package com.jpop.bookservice.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "book_service")
@Setter
@Getter
public class Book
{
    @Id
    @Column (name = "book_id")
    private Integer bookId;

    @Column (name = "book_name")
    private String bookName;

    @Column (name = "book_auther")
    private String bookAuthor;

    @Column (name = "book_category")
    private String bookCategory;

    @Column (name = "book_proce")
    private BigDecimal bookPrice;

    @Column (name = "book_description")
    private String bookDescription;
}