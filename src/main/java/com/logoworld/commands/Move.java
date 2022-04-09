package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.NotInitSurface;

import java.util.ArrayList;

public class Move implements CommandAI{
    private ArrayList<String> paramQueue = new ArrayList<String>();
    private char way;
    public String param;

    @Override
    public void getParam(String param) {
        paramQueue.add(param);
    }

    @Override
    public boolean runParam(String param) {
        this.param = param;

        switch (param){
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
                return false;
        }

        return true;
    }

    @Override
    public void action(Field field, Robot robot) throws NotInitSurface, BadCoordinates {
        runParam(paramQueue.get(0));
        paramQueue.remove(0);

        boolean isInSurface = false;

        field.hideRobot(robot);

        switch (way){
            case 'L':
                isInSurface = robot.setCoordinates(robot.X() - 1, robot.Y());
                break;
            case 'R':
                isInSurface = robot.setCoordinates(robot.X() + 1, robot.Y());
                break;
            case 'U':
                isInSurface = robot.setCoordinates(robot.X(), robot.Y() - 1);
                break;
            case 'D':
                isInSurface = robot.setCoordinates(robot.X(), robot.Y() + 1);
                break;
        }

        if(!isInSurface)
            throw new BadCoordinates(robot.X(), robot.Y(), "MOVE");

        if(!field.displayRobot(robot))
            throw new NotInitSurface("null surface of Filed", "MOVE");
    }

    @Override
    public void clone(CommandAI commandAI) throws CloneNotSupportedException {
        if(commandAI.getClass() == this.getClass()){
            this.param = ((Move) commandAI).param;
            runParam(this.param);
        } else
                throw new CloneNotSupportedException();
    }
}
