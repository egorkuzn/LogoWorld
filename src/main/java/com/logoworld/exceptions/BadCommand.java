package com.logoworld.exceptions;

public class BadCommand extends Exception{
    public BadCommand(String cmdName){
        super("Manager got bad command :: " + cmdName);
    }
}
