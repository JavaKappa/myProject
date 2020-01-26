package ru.webapp.web;

import ru.webapp.WebAppConfig;
import ru.webapp.model.ContactType;
import ru.webapp.model.Resume;
import ru.webapp.model.SectionType;
import ru.webapp.storage.IStorage;
import ru.webapp.util.SectionHtmlType;
import ru.webapp.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ResumeServlet extends javax.servlet.http.HttpServlet {
    public static IStorage storage = WebAppConfig.get().getStorage();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        Resume r = Util.isEmpty(uuid) ? new Resume(name, location) : storage.load(uuid);

        r.setFullName(name);
        r.setLocation(location);
        r.setHomePage(request.getParameter("home_page"));

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value == null || value.isEmpty()) {
                r.removeContact(type);
            } else {
                r.addContact(type, value);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (type.getHtmlType() == SectionHtmlType.ORGANIZATION) {
                continue;
            }
            if (value == null || value.isEmpty()) {
                r.getSections().remove(type);
            } else {
                r.addSection(type, type.getHtmlType().createSection(value));
            }
        }
        if (Util.isEmpty(uuid)) {
            storage.save(r);
        } else {
            storage.update(r);
        }
        response.sendRedirect("list");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        Resume r = null;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("list");
                return;
            case "create":
                r = new Resume();
                storage.save(r);
                break;
            case "view":
            case "edit":
                r = storage.load(uuid);
                break;
            default:
                throw new IllegalArgumentException();
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher("view".equals(action) ?
                "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp").forward(request, response);
    }

}
