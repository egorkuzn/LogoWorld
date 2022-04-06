package com.logoworld.exceptions;

public class BadCoordinates extends Exception{
    public BadCoordinates(int x, int y, String cmdName){
        super(cmdName + " :: bad coordinates: " + x + y);
    }
}
