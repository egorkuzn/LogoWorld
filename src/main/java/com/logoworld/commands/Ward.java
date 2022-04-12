package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.BadParam;
import com.logoworld.exceptions.NotInitSurface;

import java.util.ArrayList;

public class Ward implements CommandAI{
    @Override
    public void getParam(String param) throws BadParam {
        if(param != null)
            throw new BadParam("WARD");
    }

    @Override
    public void action(Field field, Robot robot) throws NotInitSurface {
        if(field == null || !field.isInited())
            throw new NotInitSurface("no inited", "WARD");

        if(robot == null)
            throw new NotInitSurface("robot object is NULL", "WARD");

        robot.setDrawerStatus(false);
    }

    @Override
    public void action(Field field, Robot robot, String param) throws BadParam, NotInitSurface, BadCoordinates {
        getParam(param);
        action(field, robot);
    }

    @Override
    public void clone(CommandAI commandAI) throws CloneNotSupportedException {
        if(commandAI.getClass() == this.getClass())
            return;
        else
            throw new CloneNotSupportedException();
    }
}
