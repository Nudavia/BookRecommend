package org.ldw.service;

import org.apache.ibatis.session.SqlSession;
import org.ldw.dao.BookDao;
import org.ldw.entity.Book;
import org.ldw.utils.MyBatisUtils;

import java.util.List;

public class SearchService {
    private BookDao bookDao;

    public List<Book> getBooksByCode(String keyword) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        bookDao = sqlSession.getMapper(BookDao.class);
        List<Book> bookList = bookDao.getBooksByCode(keyword);
        for (Book book : bookList) {
            System.out.println(book);
        }
        sqlSession.close();
        return bookList;
    }

}
