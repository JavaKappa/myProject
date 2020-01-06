package ru.webapp.web;

import ru.webapp.model.Resume;
import ru.webapp.storage.IStorage;
import ru.webapp.storage.SerializeFileStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


public class ResumeServlet extends javax.servlet.http.HttpServlet {
    public static IStorage storage = new SerializeFileStorage();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        Resume r = null;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("list");
                break;
            case "create":
                r = new Resume();
                storage.save(r);
                response.sendRedirect("list");
                break;
            case "view":
            case "edit":
                r = storage.load(uuid);
                break;
            default:
                throw new IllegalArgumentException();
        }
        Objects.requireNonNull(r);
        request.setAttribute("resume", r);
        request.getRequestDispatcher("view".equals(action) ?
                "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp").forward(request, response);
    }

}
