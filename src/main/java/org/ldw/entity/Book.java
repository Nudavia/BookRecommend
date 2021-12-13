package org.ldw.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String code;
    private String title;
    private String author;
    private int year;
    private String publisher;
    private String imageURL;
}
