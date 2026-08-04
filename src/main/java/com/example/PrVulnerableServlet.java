package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Intentionally vulnerable class used to demonstrate PR-time CodeQL findings.
 */
@WebServlet("/pr-vulnerable")
public class PrVulnerableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String templateName = request.getParameter("template");
        String comment = request.getParameter("comment");

        Path root = Paths.get("./templates");
        Path candidate = root.resolve(templateName);
        String content = Files.readString(candidate, StandardCharsets.UTF_8);

        response.setContentType("text/html");
        response.getWriter().println("<div>" + comment + "</div>");
        response.getWriter().println(content);
    }
}