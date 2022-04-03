package com.logoworld.managment;

import com.logoworld.commands.*;
import com.logoworld.environment.Field;
import com.logoworld.environment.Robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Manager {
    BufferedReader reader;
    Field field = null;
    Robot robot = null;
    ArrayList<CommandAI> managerTask = null;


    public Manager(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        getCommandsCall();
    }

    public void getCommandsCall(){
        try {
            String call = reader.readLine();
            run(call);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run(String call){
        String[] callArr = call.split(">");

        for (String s : callArr)
            managerTask.add(makeCommand(s));

        for(CommandAI commandAI: managerTask)
            if(commandAI != null)
                commandAI.action(field, robot);
    }

    private CommandAI makeCommand(String commandString){
        int space_idx = commandString.indexOf(" ");
        String nameOfCommand = commandString, param = null;

        if(space_idx > 0) {
            nameOfCommand = commandString.substring(0, space_idx);
            param = commandString.substring(space_idx + 1);
        }

        CommandAI commandAI_tmp;
        boolean error_catcher;
        switch(nameOfCommand){
            case "INIT":
                commandAI_tmp = new Init();
                break;
            case "MOVE":
                commandAI_tmp = new Move();
                break;
            case "DRAW":
                commandAI_tmp = new Draw();
                break;
            case "WARD":
                commandAI_tmp = new Ward();
                break;
            case "TELEPORT":
                commandAI_tmp = new Teleport();
                break;
            default:
                commandAI_tmp = null;
        }

        if(commandAI_tmp == null || !commandAI_tmp.getParam(param)) {
            commandAI_tmp = null;
            System.out.println("Maybe you made some mistake while was typing." +
                    "Don't worry: just rewrite what you want once again.");
        }

        return commandAI_tmp;
    }
}
