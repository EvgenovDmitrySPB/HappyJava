package net.proselyte.crud.controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/developer")
public class DeveloperServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String message = "Hello Dmitry GET";
        PrintWriter messageWriter = response.getWriter();
        messageWriter.println("<h1>" + message + "<h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String message = "Hello Dmitry POST";
        PrintWriter messageWriter = response.getWriter();
        messageWriter.println("<h1>" + message + "<h1>");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String message = "Hello Dmitry PUT";
        PrintWriter messageWriter = response.getWriter();
        messageWriter.println("<h1>" + message + "<h1>");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String message = "Hello Dmitry DELETE ";
        PrintWriter messageWriter = response.getWriter();
        messageWriter.println("<h1>" + message + "<h1>");
    }
}
