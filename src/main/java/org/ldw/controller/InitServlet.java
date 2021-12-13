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



@WebServlet("/InitSLT")
public class InitServlet extends HttpServlet {

    private final InitService service = new InitService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        int limit = Integer.parseInt(req.getParameter("limit"));
        System.out.println("limit:" + limit);
        List<Book> bookList = service.getHeaderBooks(limit);
        JSONArray jsonArray = JSONArray.fromObject(bookList);
        PrintWriter writer = resp.getWriter();
        writer.append(jsonArray.toString());
        System.out.println(jsonArray.toString());
    }

}
