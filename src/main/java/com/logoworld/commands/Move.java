package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.NotInitSurface;

public class Move implements CommandAI{
    char way;
    @Override
    public boolean getParam(String param) {
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
        boolean isInSurface = false;

        switch (way){
            case 'L':
                isInSurface = robot.setCoordinates(robot.X() - 1, robot.Y());
            case 'R':
                isInSurface = robot.setCoordinates(robot.X() + 1, robot.Y());
            case 'U':
                isInSurface = robot.setCoordinates(robot.X(), robot.Y() - 1);
            case 'D':
                isInSurface = robot.setCoordinates(robot.X(), robot.Y() + 1);
        }

        if(!isInSurface)
            throw new BadCoordinates(robot.X(), robot.Y(), "MOVE");

        if(!field.displayRobot(robot))
            throw new NotInitSurface("null surface of Filed", "MOVE");
    }
}
