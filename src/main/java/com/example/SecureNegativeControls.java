package com.example;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Secure comparison implementations used as negative controls for the POC.
 */
public final class SecureNegativeControls {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private SecureNegativeControls() {
    }

    public static String createSessionToken() {
        byte[] token = new byte[32];
        SECURE_RANDOM.nextBytes(token);
        return java.util.HexFormat.of().formatHex(token);
    }

    public static String readAllowedFile(String fileName) throws IOException {
        Path baseDirectory = Paths.get("./content").toAbsolutePath().normalize();
        Path candidate = baseDirectory.resolve(fileName).normalize();
        if (!candidate.startsWith(baseDirectory)) {
            throw new IllegalArgumentException("File is outside the allowed directory");
        }
        return Files.readString(candidate);
    }

    public static void redirectToKnownPage(String requestedPage, HttpServletResponse response)
            throws IOException {
        String destination = "help".equals(requestedPage) ? "/help" : "/home";
        response.sendRedirect(destination);
    }

    public static DocumentBuilderFactory createSecureXmlFactory()
            throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        factory.setXIncludeAware(false);
        factory.setExpandEntityReferences(false);
        return factory;
    }
}