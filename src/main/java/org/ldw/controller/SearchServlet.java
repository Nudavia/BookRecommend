package org.ldw.controller;

import net.sf.json.JSONArray;
import org.ldw.entity.Book;
import org.ldw.service.SearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;


@WebServlet("/SearchSLT")
public class SearchServlet extends HttpServlet {

    private static final SearchService service = new SearchService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String keyword = req.getParameter("keyword");
        System.out.println("keyword:" + keyword);
        List<Book> bookList = service.getBooksByKeywords(keyword, 8);
        JSONArray jsonArray = JSONArray.fromObject(bookList);
        PrintWriter writer = resp.getWriter();
        writer.append(jsonArray.toString());
        System.out.println(jsonArray.toString());
    }

}
