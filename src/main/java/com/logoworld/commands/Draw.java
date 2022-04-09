package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadParam;
import com.logoworld.exceptions.NotInitSurface;

public class Draw implements CommandAI{
    @Override
    public void getParam(String param) throws BadParam{
        if(param != null)
            throw new BadParam("DRAW");
    }

    @Override
    public void action(Field field, Robot robot) throws NotInitSurface {
        if(!field.isInited())
            throw new NotInitSurface("no inited", "MOVE");

        robot.setDrawerStatus(true);
    }

    @Override
    public void action(Field field, Robot robot, String param) throws BadParam, NotInitSurface {
        getParam(param);
        action(field, robot);
    }

    @Override
    public void clone(CommandAI commandAI) throws CloneNotSupportedException {
        if(commandAI.getClass() == this.getClass()){
            return;
        }else
            throw new CloneNotSupportedException();
    }
}
