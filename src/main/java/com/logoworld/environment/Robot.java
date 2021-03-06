package com.logoworld.environment;

public class Robot {
    private int x = 0, y = 0, xLimit, yLimit;
    private boolean drawerStatus;
    private String iconPath;

    public boolean getDrawerStatus(){
        return drawerStatus;
    }

    public void setDrawerStatus(boolean newStatus){
        drawerStatus = newStatus;
    }

    public void setLimits(int xLimit, int yLimit){
        this.xLimit = xLimit;
        this.yLimit = yLimit;
    }

    public boolean setCoordinates(int x, int y){
        if(x < xLimit && y < yLimit) {
            this.x = x;
            this.y = y;
            return true;
        } else
            return false;
    }

    public void moveLeft(){
        x = (x + xLimit - 1) % xLimit;
    }

    public void moveRight(){
        x = (x + xLimit + 1) % xLimit;
    }

    public void moveUp(){
        y = (y + yLimit - 1) % yLimit;
    }

    public void moveDown(){
        y = (y + yLimit + 1) % yLimit;
    }

    public int X(){
        return x;
    }

    public int Y(){
        return y;
    }
}
