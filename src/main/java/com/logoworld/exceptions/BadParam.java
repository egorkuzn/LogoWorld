package com.logoworld.exceptions;

public class BadParam extends Exception{
    public BadParam(String cmdName){
        super(cmdName);
    }
}
