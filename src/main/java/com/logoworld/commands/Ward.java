package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;

import java.util.ArrayList;

public class Ward implements CommandAI{
    private ArrayList<String> paramQueue = new ArrayList<String>();

    @Override
    public void getParam(String param) {
        paramQueue.add(param);
    }

    @Override
    public boolean runParam(String param) {
        return param == null;
    }

    @Override
    public void action(Field field, Robot robot) {
        runParam(paramQueue.get(0));
        paramQueue.remove(0);

        robot.setDrawerStatus(false);
    }

    @Override
    public void clone(CommandAI commandAI) throws CloneNotSupportedException {
        if(commandAI.getClass() == this.getClass())
            return;
        else
            throw new CloneNotSupportedException();
    }
}
