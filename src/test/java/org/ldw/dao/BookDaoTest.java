package org.ldw.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.ldw.utils.MySQLClient;

import org.ldw.entity.Book;

import java.util.List;
import java.util.TreeSet;

public class BookDaoTest {

    @Test
    public void test1() {
        SqlSession sqlSession = MySQLClient.getSqlSession();
        BookDao bookDao = sqlSession.getMapper(BookDao.class);
        List<Book> bookList = bookDao.getBooksByCode("0002005018");
        for (Book book : bookList) {
            System.out.println(book);
        }
        sqlSession.close();
    }

    @Test
    public void test2() {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("0553281739");
        treeSet.add("0960989803");
        treeSet.add("0915560186");
        treeSet.add("157748617X");
        treeSet.add("082480287X");
        treeSet.add("0373703198");

        for (String s : treeSet) {
            System.out.println(s);
        }
        System.out.println();
        TreeSet<String> treeSet2 = new TreeSet<>();
        treeSet2.add("0553281739");
        treeSet2.add("0960989803");
        treeSet2.add("0915560186");
        treeSet.retainAll(treeSet2);
        for (String s : treeSet) {
            System.out.println(s);
        }
    }
}
