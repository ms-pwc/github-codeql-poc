package com.example;

/**
 * Entry point for the sample application.
 */
public final class App {
    private App() {
        // Utility class.
    }

    public static void main(String[] args) {
        String name = args.length > 0 ? args[0] : "World";
        System.out.println("Hello, " + name + "!");
    }
}