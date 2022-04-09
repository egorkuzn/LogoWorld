package com.logoworld;

import com.logoworld.commands.CommandAI;
import com.logoworld.environment.CellType;
import com.logoworld.environment.DisplayedSurface;
import com.logoworld.managment.Manager;

public class Main {
        public static void main(String[] args) {
                Manager manager = new Manager();
                manager.getCommandsCall("C:\\Users\\egork\\IdeaProjects\\LogoWorld\\src\\main\\java\\com\\logoworld\\managment\\todo.txt");
        }
}
