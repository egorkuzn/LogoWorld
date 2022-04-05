package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;

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
    public void action(Field field, Robot robot) {
        switch (way){
            case 'L':
                robot.setCoordinates(robot.X() - 1, robot.Y());
            case 'R':
                robot.setCoordinates(robot.X() + 1, robot.Y());
            case 'U':
                robot.setCoordinates(robot.X(), robot.Y() - 1);
            case 'D':
                robot.setCoordinates(robot.X(), robot.Y() + 1);
        }

        field.displayRobot(robot);
    }
}
