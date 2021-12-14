package org.ldw.service;

import org.apache.ibatis.session.SqlSession;
import org.ldw.dao.BookDao;
import org.ldw.dao.IndexDao;
import org.ldw.entity.Book;
import org.ldw.entity.Index;
import org.ldw.utils.MySQLClient;

import java.util.*;
import java.util.stream.Collectors;

public class SearchService {
    private BookDao bookDao;
    private Map<String, Set<String>> IndexList;

    public SearchService() {
        IndexList = IndexDao.getIndices();
    }

    public List<Book> getBooksByCode(String code) {
        SqlSession sqlSession = MySQLClient.getSqlSession();
        bookDao = sqlSession.getMapper(BookDao.class);
        List<Book> bookList = bookDao.getBooksByCode(code);
        for (Book book : bookList) {
            System.out.println(book);
        }
        sqlSession.close();
        return bookList;
    }


    public List<Book> getBooksByKeywords(String keyword, int topK) {
        String[] words = keyword.toLowerCase().split("[\\s+(){}\\[\\]\"?<>|'.,:*!@~`%&^/\\\\\\-$#]");
        Set<String> bookCodeSet = new TreeSet<>();
        for (String word : words) {
            Set<String> codeSet = IndexList.get(word);
            if (codeSet == null)
                continue;
            if (bookCodeSet.isEmpty()) {
                bookCodeSet.addAll(codeSet);
            } else {
                bookCodeSet.retainAll(codeSet);
            }
        }
        List<Book> bookList = new ArrayList<>();

        for (String code : bookCodeSet) {
            bookList.addAll(getBooksByCode(code));
        }

        return bookList.stream().sorted(Comparator.comparingInt(o -> o.getTitle().length()))
                .collect(Collectors.toList()).subList(0, Math.min(topK, bookList.size()));
    }

}
