package com.github.rogerli.system;

import java.util.Arrays;
import java.util.List;

public class RegexTest {

    public static void main(String[] args) {

        List<String> pathSamples = Arrays.asList(
                "/index",
                "/index/",
                "/index/**",
                "/api",
                "/api/",
                "/api/**");

        for (String pathSample : pathSamples) {
            System.out.println("Path sample: " + pathSample
                    + " - SEC: " + pathSample.matches("^/api.*$")
                    + " | FRAUD: " + pathSample.matches("^(?!/api).*$"));
        }
    }
}