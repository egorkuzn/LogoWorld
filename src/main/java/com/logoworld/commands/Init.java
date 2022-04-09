package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.BadParam;
import com.logoworld.exceptions.NotInitSurface;

public class Init implements CommandAI{
    private int height;
    private int width;
    private int x;
    private int y;
    public String param = null;

    private boolean checkArgs(){
        if(height <= 0)
            return false;
        if(width <= 0)
            return false;
        if(x > width || x < 0)
            return false;
        if(y > height || y < 0)
            return false;

        return true;
    }

    private int convert(String str) {
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
        if(!param.matches("^\\d+\\s+\\d+\\s+\\d+\\s+\\d$")) {
            throw new BadParam("INIT");
        }

        this.param = param;
        String[] arr = param.split(" ");

        width = convert(arr[0]);
        height = convert(arr[1]);
        x = convert(arr[2]);
        y = convert(arr[3]);

        if(!checkArgs()){
            throw new BadParam("INIT");
        }
    }

    @Override
    public void action(Field field, Robot robot) throws BadCoordinates, NotInitSurface {
        try {
            field.setDisplayedSurface(width, height, robot);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        robot.setCoordinates(x,y);
        if(!field.displayRobot(robot))
            throw new NotInitSurface("null surface of Filed", "INIT");
    }

    @Override
    public void action(Field field, Robot robot, String param) throws BadParam, NotInitSurface, BadCoordinates {
        getParam(param);
        action(field, robot);
    }

    @Override
    public void clone(CommandAI commandAI) throws CloneNotSupportedException, BadParam {
        if(commandAI.getClass() == this.getClass()){
            this.param = ((Init) commandAI).param;
            getParam(this.param);
        } else
            throw new CloneNotSupportedException();
    }
}
