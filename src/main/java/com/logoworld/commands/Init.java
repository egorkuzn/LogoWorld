package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;

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

        height = convert(arr[0]);
        width = convert(arr[1]);
        x = convert(arr[2]);
        y = convert(arr[3]);

        return true;
    }

    @Override
    public void action(Field field, Robot robot) {

    }
}
