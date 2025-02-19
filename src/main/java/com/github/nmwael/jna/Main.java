package com.github.nmwael.jna;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Process process = new ProcessBuilder("java", "-version").start();

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("/bin/bash", "-c", "echo $GREETING");
        process = processBuilder.start();
        System.out.printf("Hello and welcome!");
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }
}