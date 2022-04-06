package com.logoworld.environment;

import com.logoworld.exceptions.BadCoordinates;

public class Robot {
    private int x, y, xLimit, yLimit;
    private boolean drawerStatus;
    private String iconPath;

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

    public int X(){
        return x;
    }

    public int Y(){
        return y;
    }
}
