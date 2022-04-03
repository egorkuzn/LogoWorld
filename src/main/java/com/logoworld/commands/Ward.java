package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;

public class Ward implements CommandAI{
    @Override
    public boolean getParam(String param) {
        return param == null;
    }

    @Override
    public void action(Field field, Robot robot) {

    }
}
