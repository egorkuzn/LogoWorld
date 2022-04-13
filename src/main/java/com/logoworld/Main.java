package com.logoworld;

import com.logoworld.managment.Manager;

import java.util.logging.Logger;

/**
*There is some example how run {@code "todo.txt"} file:
**/
public class Main {
        public static void main(String[] args) {
                Manager manager = new Manager();
                //absolute path to "todo.txt"
                manager.getCommandsCall("C:\\Users\\egork\\IdeaProjects\\LogoWorld\\src\\main\\resources\\todo.txt");
                //running of "todo.txt" from resources
                manager.getCommandsCall();
        }
}
