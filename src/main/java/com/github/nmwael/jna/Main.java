package com.github.nmwael.jna;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Process process = new ProcessBuilder("/usr/bin/echo", "hello").start();

    }
}