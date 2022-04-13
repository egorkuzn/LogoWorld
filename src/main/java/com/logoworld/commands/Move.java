package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.BadParam;
import com.logoworld.exceptions.NotInitSurface;

import java.util.ArrayList;

public class Move implements CommandAI{
    private char way;
    private int steps;
    public String param;

    private int convert(String str)
    {
        int value = 0;
        System.out.println("String = " + str);

        // Convert the String
        try {
            value = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {

            // This is thrown when the String
            // contains characters other than digits
            System.out.println("Invalid String");
        }
        return value;
    }

    @Override
    public void getParam(String param) throws BadParam {
        if(param == null || !param.matches("^\\w+\\s+[1-9]\\d*$"))
            throw new BadParam("MOVE");

        String[] arr = param.split("\\s+");

        switch (arr[0]){
            case "L":
                way = 'L';
                break;
            case "R":
                way = 'R';
                break;
            case "U":
                way = 'U';
                break;
            case "D":
                way = 'D';
                break;
            default:
                throw new BadParam("MOVE :: bad symbol of direction");
        }

        steps = Integer.parseInt(arr[1]);

        if(steps < 0)
            throw new BadParam("MOVE :: negative count of steps");
    }

    @Override
    public void action(Field field, Robot robot) throws NotInitSurface, BadCoordinates {
        if(field == null || !field.isInited())
            throw new NotInitSurface("no INITed", "MOVE");

        for(int i = 0; i < steps; ++i){
            field.hideRobot(robot);

            switch (way){
                case 'L':
                    robot.moveLeft();
                    break;
                case 'R':
                    robot.moveRight();
                    break;
                case 'U':
                    robot.moveUp();
                    break;
                case 'D':
                    robot.moveDown();
                    break;
            }

            if(robot == null || !field.displayRobot(robot))
                throw new NotInitSurface("null surface of Filed", "MOVE");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void action(Field field, Robot robot, String param) throws BadParam, NotInitSurface, BadCoordinates {
        getParam(param);
        action(field, robot);
    }

    @Override
    public void clone(CommandAI commandAI) throws CloneNotSupportedException, BadParam {
        if(commandAI.getClass() == this.getClass()){
            this.param = ((Move) commandAI).param;
            getParam(this.param);
        } else
                throw new CloneNotSupportedException();
    }
}
