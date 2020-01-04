package ru.webapp.web;

import ru.webapp.storage.WebAppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class ResumeServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        try (Writer writer = response.getWriter()){
            String name = request.getParameter("name");
            writer.write("Тест сервлет: привет Web =) иххххаааа  " + name);
        } catch (IOException e) {
            throw new WebAppException("get writer failed", e);
        }
    }

}
