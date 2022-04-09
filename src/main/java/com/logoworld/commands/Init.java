package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.NotInitSurface;

import java.util.ArrayList;

public class Init implements CommandAI{
    private ArrayList<String> paramQueue = new ArrayList<String>();
    private int height;
    private int width;
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
        if(!param.matches("^\\d+\\s+\\d+\\s+\\d+\\s+\\d$"))
            return false;

        this.param = param;
        String[] arr = param.split(" ");

        width = convert(arr[0]);
        height = convert(arr[1]);
        x = convert(arr[2]);
        y = convert(arr[3]);

        return true;
    }

    @Override
    public void action(Field field, Robot robot) throws BadCoordinates, NotInitSurface {
        runParam(paramQueue.get(0));
        paramQueue.remove(0);

        robot.setLimits(width, height);

        try {
            if(!robot.setCoordinates(x,y))
                throw new BadCoordinates(x, y, "INIT");
            field.setDisplayedSurface(width, height, robot);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BadCoordinates e){
            e.printStackTrace();
        }

        if(!field.displayRobot(robot))
            throw new NotInitSurface("null surface of Filed", "INIT");

        if(!robot.setCoordinates(x, y))
            throw new BadCoordinates(x, y, "INIT");
    }

    @Override
    public void clone(CommandAI commandAI) throws CloneNotSupportedException {
        if(commandAI.getClass() == this.getClass()){
            this.param = ((Init) commandAI).param;
            runParam(this.param);
        } else
            throw new CloneNotSupportedException();
    }
}
