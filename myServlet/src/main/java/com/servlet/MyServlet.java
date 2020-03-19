package com.servlet;

import com.servlet.entity.Book;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="myServlet", urlPatterns = {"/myServlet"})
public class MyServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(MyServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        System.out.println("****************************title=" + title);
        String errorMsg = null;
        /*
        if(null == title || title.trim().equals("")){
            errorMsg = "title shouldn't be null or empty";
        }
         */

        if(errorMsg != null){
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/index.jsp");
            PrintWriter out = resp.getWriter();
            out.println("<font color='red'>" + errorMsg + "</font>");
            rd.include(req, resp);
        }else{
            Connection conn = (Connection) this.getServletContext().getAttribute("DBConnection");
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement("select id, title, author, publishedDate from books where title like ?");
                ps.setString(1, "%"+title+"%");
                rs = ps.executeQuery();

                if(rs != null){
                    List<Book> books = new ArrayList<>();
                    while (rs.next()){
                        Book book = new Book(rs.getString("id"), rs.getString("title"),rs.getString("author"), rs.getString("publishedDate"));
                        books.add(book);
                    }
                    req.setAttribute("bookList", books);
                    this.getServletContext().getRequestDispatcher("/listbook.jsp").forward(req, resp);
                }else {
                    RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/index.jsp");
                    PrintWriter out = resp.getWriter();
                    logger.error("Book not found with the title=" + title);
                    out.println("<font color='red'>No book found with given title, please try another title......</font>");
                    rd.include(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
