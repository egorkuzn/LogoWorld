package com.logoworld.environment;

import com.logoworld.exceptions.NotInitSurface;

public class Field {
    private int height, width;
    private DisplayedSurface surface = null;

    public void setDisplayedSurface(int height, int width, Robot robot) throws InterruptedException {
        surface = new DisplayedSurface(width, height, 50);
        robot.setLimits(height, width);
    }

    public boolean displayRobot(Robot robot){
        if(surface != null){
            surface.setCell(robot.X(), robot.Y(), CellType.ROBOT);
            return true;
        } else
            return false;
    }
}
