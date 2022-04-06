package com.logoworld.exceptions;

public class NotInitSurface extends Exception{
    public NotInitSurface(String message, String cmdName){
        super(cmdName +" :: "+ message);
    }
}
