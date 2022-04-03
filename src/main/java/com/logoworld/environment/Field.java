package com.logoworld.environment;

public class Field {
    private int height, width;

    Field() throws InterruptedException {
        DisplayedSurface surface = new DisplayedSurface(height, width, 50);
    }
}
