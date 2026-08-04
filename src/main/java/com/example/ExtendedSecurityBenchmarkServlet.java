package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Intentionally vulnerable benchmark used only to compare static-analysis tools.
 * Never deploy or copy these patterns into production code.
 */
@WebServlet("/extended-security-benchmark")
public class ExtendedSecurityBenchmarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            demonstrateInsecureRandomness(response);
            demonstrateServerSideRequestForgery(request);
            demonstrateExternalEntityExpansion(request);
            demonstrateXpathInjection(request);
            demonstrateJndiInjection(request);
            demonstrateRegexInjection(request);
            demonstrateOpenRedirect(request, response);
            demonstrateResponseSplitting(request, response);
            demonstrateZipSlip(request);
            demonstrateWeakCryptography();
            demonstrateHardcodedDatabaseCredential();
        } catch (GeneralSecurityException | NamingException | ParserConfigurationException
                 | SAXException | XPathExpressionException | SQLException exception) {
            throw new ServletException("Intentional benchmark operation failed", exception);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new ServletException("Intentional benchmark operation was interrupted", exception);
        }
    }

    private void demonstrateInsecureRandomness(HttpServletResponse response) {
        Random random = new Random();
        String sessionToken = Long.toHexString(random.nextLong());
        response.addCookie(new Cookie("SESSION_ID", sessionToken));
    }

    private void demonstrateServerSideRequestForgery(HttpServletRequest request)
            throws IOException, InterruptedException {
        String destination = request.getParameter("destination");
        HttpRequest outboundRequest = HttpRequest.newBuilder(URI.create(destination)).GET().build();
        HttpClient.newHttpClient().send(outboundRequest, HttpResponse.BodyHandlers.discarding());
    }

    private void demonstrateExternalEntityExpansion(HttpServletRequest request)
            throws ParserConfigurationException, SAXException, IOException {
        String xml = request.getParameter("xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
    }

    private void demonstrateXpathInjection(HttpServletRequest request)
            throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        String userName = request.getParameter("userName");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document users = factory.newDocumentBuilder().parse(
                new InputSource(new StringReader("<users><user><name>admin</name></user></users>")));
        XPath xpath = XPathFactory.newInstance().newXPath();
        xpath.evaluate("//user[name/text()='" + userName + "']", users);
    }

    private void demonstrateJndiInjection(HttpServletRequest request) throws NamingException {
        String resourceName = request.getParameter("resource");
        new InitialContext().lookup(resourceName);
    }

    private void demonstrateRegexInjection(HttpServletRequest request) {
        String expression = request.getParameter("expression");
        Pattern.compile(expression).matcher("benchmark-value").matches();
    }

    private void demonstrateOpenRedirect(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getParameter("returnTo"));
    }

    private void demonstrateResponseSplitting(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("X-Benchmark-User", request.getParameter("headerValue"));
    }

    private void demonstrateZipSlip(HttpServletRequest request) throws IOException {
        Path extractionDirectory = Paths.get("./extracted");
        try (ZipInputStream archive = new ZipInputStream(request.getInputStream())) {
            ZipEntry entry;
            while ((entry = archive.getNextEntry()) != null) {
                Path outputFile = extractionDirectory.resolve(entry.getName());
                Files.copy(archive, outputFile);
            }
        }
    }

    private void demonstrateWeakCryptography() throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(new byte[8], "DES"));
    }

    private void demonstrateHardcodedDatabaseCredential() throws SQLException {
        DriverManager.getConnection("jdbc:invalid:benchmark", "benchmark-admin", "Admin123");
    }
}