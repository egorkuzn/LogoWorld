package com.logoworld.managment;

import com.logoworld.commands.*;
import com.logoworld.environment.Field;
import com.logoworld.environment.Robot;

import java.io.*;
import java.util.*;

public class Manager {
    BufferedReader reader;
    Field field = null;
    Robot robot = null;
    ArrayList<CommandAI> managerTask = null;
    HashMap<String, CommandAI> cashHistory = new HashMap<String, CommandAI>();

    public Manager(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        getAllCommands();
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

        CommandAI commandAI = null;

        if(cashHistory.containsKey(nameOfCommand)) {
            commandAI = cashHistory.get(nameOfCommand);
            commandAI.getParam(param);
        }

        return commandAI;
    }

    private void getAllCommands(){
        InputStream inputStream;

        try{
            Properties prop = new Properties();
            final String propFileName = "commands.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if(inputStream != null)
                prop.load(inputStream);
            else
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");


            for (Map.Entry<?,?> entry: prop.entrySet()){
                CommandAI obj = (CommandAI) Class.forName((String) entry.getValue()).newInstance();
                cashHistory.put((String)entry.getKey(), obj);
            }
        } catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
