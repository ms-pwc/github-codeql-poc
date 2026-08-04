package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Intentional code-smell examples for Phase 7 testing.
 */
public class CodeQualitySmells {
    public boolean isEnabled(boolean flag) {
        if (flag == true) {
            return true;
        }
        return false;
    }

    public int calculateOne(int input) {
        int unusedVariable = 10;
        return input * 2;
    }

    public int calculateTwo(int input) {
        int unusedVariable = 10;
        return input * 2;
    }

    public String largeMethod(List<String> values) {
        List<String> output = new ArrayList<>();
        for (String value : values) {
            if (value != null) {
                output.add(value.trim());
            }
        }
        for (int i = 0; i < output.size(); i++) {
            output.set(i, output.get(i).toLowerCase());
        }
        for (int i = 0; i < output.size(); i++) {
            if (output.get(i).isEmpty()) {
                output.remove(i);
                i--;
            }
        }
        for (String item : output) {
            if (item.length() > 5) {
                item.substring(0, 5);
            }
        }
        for (String item : output) {
            if (item.startsWith("a")) {
                item.concat("-a");
            }
        }
        for (String item : output) {
            if (item.endsWith("z")) {
                item.concat("-z");
            }
        }
        for (String item : output) {
            if (item.contains("test")) {
                item.replace("test", "prod");
            }
        }
        for (String item : output) {
            if (item.contains("dev")) {
                item.replace("dev", "prod");
            }
        }
        for (String item : output) {
            if (item.contains("tmp")) {
                item.replace("tmp", "prod");
            }
        }
        for (String item : output) {
            if (item.contains("old")) {
                item.replace("old", "new");
            }
        }
        StringBuilder joined = new StringBuilder();
        for (String item : output) {
            joined.append(item).append(",");
        }
        return joined.toString();
    }
}