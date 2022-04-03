package com.logoworld;

import com.logoworld.environment.DisplayedSurface;

public class Main {
        public static void main(String[] args) throws InterruptedException {
            String example = "10 551 1111111 1";
            System.out.println(
                    "My Message:: " +
                    example.matches("^\\d+\\s+\\d+\\s+\\d+\\s+\\d$"));
        }
}
