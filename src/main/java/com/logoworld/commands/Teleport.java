package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.BadParam;
import com.logoworld.exceptions.NotInitSurface;

public class Teleport implements CommandAI{
    private int x;
    private int y;
    public String param = null;

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
        if(param == null || !param.matches("^0\\s+0$")
                && !param.matches("^0\\s+[1-9]\\d*$")
                && !param.matches("^[1-9]\\d*\\s+[1-9]\\d*$")
                && !param.matches("^[1-9]\\d*\\s+0$"))
            throw new BadParam("TELEPORT");

        String[] arr = param.split("\\s+");

        x = convert(arr[0]);
        y = convert(arr[1]);
    }

    @Override
    public void action(Field field, Robot robot) throws BadCoordinates, NotInitSurface {
        if(field == null || !field.isInited())
            throw new NotInitSurface("no inited", "TELEPORT");

        if(robot == null)
            throw new NotInitSurface("null surface of Field", "TELEPORT");

        field.hideRobot(robot);

        if(!robot.setCoordinates(x, y))
            throw new BadCoordinates(robot.X(), robot.Y(), "TELEPORT");
        if(!field.displayRobot(robot))
            throw new NotInitSurface("null surface of Field", "TELEPORT");
    }

    @Override
    public void action(Field field, Robot robot, String param) throws BadParam, NotInitSurface, BadCoordinates {
        getParam(param);
        action(field, robot);
    }

    @Override
    public void clone(CommandAI commandAI) throws CloneNotSupportedException, BadParam {
        if(commandAI.getClass() == this.getClass()){
            this.param = ((Teleport) commandAI).param;
            getParam(this.param);
        } else
            throw new CloneNotSupportedException();
    }
}
