package org.ldw.dao;

import org.apache.ibatis.annotations.Param;

import org.ldw.entity.Book;
import java.util.List;

public interface BookDao {

    List<Book> getBookByCode(@Param("ISBN") String code);
}

