package org.ldw.dao;

import org.apache.ibatis.annotations.Param;

import org.ldw.entity.Book;

import java.util.List;

public interface BookDao {

    List<Book> getBooksByCode(@Param("ISBN") String code);

    List<Book> getHeaderBooks(@Param("limit") int limit);
}

