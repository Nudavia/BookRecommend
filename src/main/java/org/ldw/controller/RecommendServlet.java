package org.ldw.controller;

import net.sf.json.JSONArray;
import org.ldw.entity.Book;
import org.ldw.service.InitService;
import org.ldw.service.SearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/RecommendSLT")
public class RecommendServlet extends HttpServlet {
    private final SearchService service = new SearchService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String code = req.getParameter("code");
        System.out.println("code:" + code);
        List<Book> bookList = service.getBooksByCode(code);
        JSONArray jsonArray = JSONArray.fromObject(bookList);
        PrintWriter writer = resp.getWriter();
        writer.append(jsonArray.toString());
        System.out.println(jsonArray.toString());
    }

}
