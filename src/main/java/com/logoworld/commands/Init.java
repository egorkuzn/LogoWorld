package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.NotInitSurface;

public class Init implements CommandAI{
    private int height;
    private int width;
    private int x;
    private int y;

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
    public boolean getParam(String param) {
        if(!param.matches("^\\d+\\s+\\d+\\s+\\d+\\s+\\d$"))
            return false;

        String[] arr = param.split(" ");

        width = convert(arr[0]);
        height = convert(arr[1]);
        x = convert(arr[2]);
        y = convert(arr[3]);

        return true;
    }

    @Override
    public void action(Field field, Robot robot) throws BadCoordinates, NotInitSurface {
        try {
            field.setDisplayedSurface(width, height, robot);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(!field.displayRobot(robot))
            throw new NotInitSurface("null surface of Filed", "INIT");

        if(!robot.setCoordinates(x, y))
            throw new BadCoordinates(x, y, "INIT");
    }
}
