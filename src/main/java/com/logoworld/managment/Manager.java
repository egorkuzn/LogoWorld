package com.logoworld.managment;

import com.logoworld.commands.*;
import com.logoworld.environment.Field;
import com.logoworld.environment.Robot;
import com.logoworld.exceptions.BadCommand;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.BadParam;
import com.logoworld.exceptions.NotInitSurface;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class Manager {
    private BufferedReader reader = null;
    private Field field = new Field();
    private Robot robot = new Robot();
    private String toDo = "/todo.txt";
    private ArrayList<CommandAI> managerTask = new ArrayList<CommandAI>();
    private HashMap<String, CommandAI> cashHistory = new HashMap<String, CommandAI>();

    public Manager(){
        getAllCommands();
    }

    public void getCommandsCall(String toDo){

        try {
            reader = new BufferedReader(new FileReader(toDo));
            String call = reader.readLine();
            run(call);
        } catch (IOException e) {
            System.out.println("Bad path to file. Redirecting to default todo...");
            getCommandsCall();
        }
    }

    public void getCommandsCall(){
        try {
            URL path = getClass().getResource(toDo);
            reader = new BufferedReader(new FileReader(path.getPath()));
            String call = reader.readLine();
            run(call);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run(String call){
        String[] callArr = call.split("\\s*+>+\\s*");

        for (String s : callArr) {
            try {
                runCommand(s, field, robot);
            } catch (BadCommand e) {
                e.printStackTrace();
            }
        }
    }

    private void runCommand (String commandString, Field field, Robot robot) throws BadCommand {
        int space_idx = commandString.indexOf(" ");
        String nameOfCommand = commandString, param = null;

        if(space_idx > 0) {
            nameOfCommand = commandString.substring(0, space_idx);
            param = commandString.substring(space_idx + 1);
        }

        CommandAI commandAI = null; //exception

        if(doesExist(nameOfCommand)) {
            commandAI = cashHistory.get(nameOfCommand);
            try {
                commandAI.action(field, robot, param);
                Thread.sleep(250);
            } catch (BadCoordinates e) {
                e.printStackTrace();
            } catch (BadParam e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NotInitSurface e) {
                e.printStackTrace();
            }
        } else{
            throw new BadCommand(nameOfCommand);
        }
    }

    private boolean doesExist(String nameOfCommand){
        if(cashHistory.containsKey(nameOfCommand)) {
            return cashHistory.get(nameOfCommand) != null;
        }
        else {
            tryToFindCommand(nameOfCommand);
            return cashHistory.containsKey(nameOfCommand) && (cashHistory.get(nameOfCommand) != null);
        }
    }

    private void tryToFindCommand(String nameOfCommand){
        InputStream inputStream = null;
        CommandAI commandAI = null;

        try {
            Properties prop = new Properties();
            final String propFileName = "additional.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if(inputStream != null)
                prop.load(inputStream);
            else
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");

            final String commandPath = prop.getProperty(nameOfCommand);
            commandAI = (CommandAI) Class.forName(commandPath).getDeclaredConstructor().newInstance();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
        }

        try {
            if(commandAI == null)
                throw new BadCommand(nameOfCommand);
            else
                cashHistory.put(nameOfCommand, commandAI);
        } catch (BadCommand e) {
            e.printStackTrace();
        }
    }

    private void getAllCommands(){
        InputStream inputStream = null;

        try{
            Properties prop = new Properties();
            final String propFileName = "commands.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if(inputStream != null)
                prop.load(inputStream);
            else
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");


            for (Map.Entry<?,?> entry: prop.entrySet()){
                //take constructor of class
                CommandAI obj = (CommandAI) Class.forName((String) entry.getValue()).getDeclaredConstructor().newInstance();
                cashHistory.put((String)entry.getKey(), obj);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
