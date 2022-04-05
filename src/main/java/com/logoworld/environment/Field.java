package com.logoworld.environment;

public class Field {
    private int height, width;
    private DisplayedSurface surface = null;

    public void setDisplayedSurface(int height, int width) throws InterruptedException {
        surface = new DisplayedSurface(width, height, 50);
    }

    public void displayRobot(Robot robot){
        if(surface == null){
            surface.setCell(robot.X(), robot.Y(), CellType.ROBOT);
        }

    }
}
