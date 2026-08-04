package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Random;

/**
 * Intentionally vulnerable endpoint for CodeQL testing only.
 * Do not deploy this class or use these patterns in production code.
 */
@WebServlet("/advanced-vulnerable")
public class AdvancedVulnerableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = request.getParameter("page");
        String name = request.getParameter("name");
        String payload = request.getParameter("payload");

        // Path traversal: user-controlled path segment is appended to a base directory.
        Path baseDirectory = Paths.get("./content");
        Path selectedFile = baseDirectory.resolve(page);
        String fileContent = Files.readString(selectedFile, StandardCharsets.UTF_8);

        // Weak random: predictable output unsuitable for security-sensitive values.
        Random weakRandom = new Random();
        int token = weakRandom.nextInt(1_000_000);

        // XSS: untrusted input is written directly into an HTML response.
        response.setContentType("text/html");
        response.getWriter().println("<h2>Hello " + name + "</h2>");

        // Unsafe deserialization: user-controlled bytes are deserialized.
        byte[] raw = Base64.getDecoder().decode(payload);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(raw))) {
            Object deserialized = objectInputStream.readObject();
            response.getWriter().println("Loaded file bytes: " + fileContent.length());
            response.getWriter().println("Token: " + token);
            response.getWriter().println("Object type: " + deserialized.getClass().getName());
        } catch (ClassNotFoundException exception) {
            throw new ServletException("Failed to deserialize payload", exception);
        }
    }
}