package org.ldw.service;

import org.apache.ibatis.session.SqlSession;
import org.ldw.dao.BookDao;
import org.ldw.entity.Book;
import org.ldw.utils.MySQLClient;

import java.util.List;

public class InitService {
    private BookDao bookDao;

    public InitService() {
//        HDFSClient hdfsClient = HDFSClient.getHdfsClient();
//        hdfsClient.initConnection();
//        hdfsClient.closeConnection();
    }

    public List<Book> getHeaderBooks(int limit) {
        SqlSession sqlSession = MySQLClient.getSqlSession();
        bookDao = sqlSession.getMapper(BookDao.class);
        List<Book> bookList = bookDao.getHeaderBooks(limit);
        for (Book book : bookList) {
            System.out.println(book);
        }
        sqlSession.close();
        return bookList;
    }
}
