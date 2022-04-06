package com.logoworld.managment;

import com.logoworld.commands.*;
import com.logoworld.environment.Field;
import com.logoworld.environment.Robot;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.NotInitSurface;

import java.io.*;
import java.util.*;

public class Manager {
    private BufferedReader reader;
    private Field field = new Field();
    private Robot robot = null;
    private ArrayList<CommandAI> managerTask = null;
    private HashMap<String, CommandAI> cashHistory = new HashMap<String, CommandAI>();

    public Manager(){
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\egork\\IdeaProjects\\LogoWorld\\src\\main\\java\\com\\logoworld\\managment\\todo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
            if(commandAI != null) {
                try {
                    commandAI.action(field, robot);
                } catch (NotInitSurface e) {
                    e.printStackTrace();
                } catch (BadCoordinates e) {
                    e.printStackTrace();
                }
            }
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
//think about "else" block there
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
