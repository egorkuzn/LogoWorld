package com.logoworld.environment;

public class Field {
    private int height, width;
    private DisplayedSurface surface = null;
    private boolean[][] coloredPoints = null;

    public void setDisplayedSurface(int width, int height, Robot robot) throws InterruptedException {
        surface = new DisplayedSurface(width, height, 50);
        robot.setLimits(width, height);
        coloredPoints = new boolean[height][width];
        initColoredPoints();
    }

    public boolean hideRobot(Robot robot){
        if(surface != null){
            if(robot.getDrawerStatus())
                surface.setCell(robot.X(), robot.Y(), CellType.COLOR);
            else
                surface.setCell(robot.X(), robot.Y(), CellType.PURITY);

            return true;
        } else
            return false;
    }

    public boolean displayRobot(Robot robot){
        if(surface != null){
            if(robot.getDrawerStatus())
                addColoredPoint(robot.X(), robot.Y());

            if(isColoredPoint(robot.X(),robot.Y()))
                surface.setCell(robot.X(), robot.Y(), CellType.COLORED_ROBOT);
            else
                surface.setCell(robot.X(), robot.Y(), CellType.ROBOT);

            return true;
        } else
            return false;
    }

    private void initColoredPoints(){
        for(int y = 0; y < height; ++y)
            for(int x= 0; x < width; ++x)
                coloredPoints[y][x] = false;
    }

    private void addColoredPoint(int x, int y){
        coloredPoints[y][x] = true;
    }

    private boolean isColoredPoint(int x, int y){
        return coloredPoints[y][x];
    }
}
