package com.logoworld.environment;

public class Robot {
    private int x, y;
    private boolean drawerStatus;
    private String iconPath;

    public void setDrawerStatus(boolean newStatus){
        drawerStatus = newStatus;
    }

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int X(){
        return x;
    }

    public int Y(){
        return y;
    }
}
