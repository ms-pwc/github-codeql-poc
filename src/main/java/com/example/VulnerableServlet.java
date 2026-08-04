package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Intentionally vulnerable endpoint for validating CodeQL detection.
 * Do not deploy this class or copy these patterns into an application.
 */
@WebServlet("/vulnerable")
public class VulnerableServlet extends HttpServlet {
    private static final String PASSWORD = "Admin123";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userInput = request.getParameter("input");

        try (Connection connection = DriverManager.getConnection("jdbc:invalid:demo");
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM users WHERE id=" + userInput;
            statement.executeQuery(query);
        } catch (SQLException exception) {
            throw new ServletException("Demonstration database call failed", exception);
        }

        Runtime.getRuntime().exec(userInput);
        response.getWriter().println("Configured password: " + PASSWORD);
    }
}