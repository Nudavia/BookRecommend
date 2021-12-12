package org.ldw.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ldw.utils.MyBatisUtils;

import org.ldw.entity.Book;
import java.util.List;

public class BookDaoTest {

    @Test
    public void test1() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        BookDao bookDao = sqlSession.getMapper(BookDao.class);
        List<Book> bookList = bookDao.getBookByCode("0002005018");
        for (Book book : bookList) {
            System.out.println(book);
        }
        sqlSession.close();
    }
}
