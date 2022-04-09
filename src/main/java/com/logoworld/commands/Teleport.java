package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.NotInitSurface;

import java.util.ArrayList;

public class Teleport implements CommandAI{
    private ArrayList<String> paramQueue = new ArrayList<String>();
    private int x;
    private int y;
    public String param = null;

    public int convert(String str)
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
    public void getParam(String param) {
        paramQueue.add(param);
    }

    @Override
    public boolean runParam(String param) {
        if(!param.matches("^\\d\\s\\d$"))
            return false;

        String[] arr = param.split(" ");
        x = convert(arr[0]);
        y = convert(arr[1]);

        return true;
    }

    @Override
    public void action(Field field, Robot robot) throws BadCoordinates, NotInitSurface {
        runParam(paramQueue.get(0));
        paramQueue.remove(0);

        field.hideRobot(robot);

        if(!robot.setCoordinates(x, y))
            throw new BadCoordinates(robot.X(), robot.Y(), "TELEPORT");
        if(!field.displayRobot(robot))
            throw new NotInitSurface("null surface of Field", "TELEPORT");
    }

    @Override
    public void clone(CommandAI commandAI) throws CloneNotSupportedException {
        if(commandAI.getClass() == this.getClass()){
            this.param = ((Teleport) commandAI).param;
            runParam(this.param);
        } else
            throw new CloneNotSupportedException();
    }
}
