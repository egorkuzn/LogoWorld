package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;

public class Teleport implements CommandAI{
    public int x;
    public int y;

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
        if(!param.matches("^\\d\\s\\d$"))
            return false;

        String[] arr = param.split(" ");
        x = convert(arr[0]);
        y = convert(arr[1]);

        return true;
    }

    @Override
    public void action(Field field, Robot robot) {

    }
}
