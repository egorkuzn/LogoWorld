package com.logoworld.commands;

import com.logoworld.environment.Field;
import com.logoworld.environment.Robot;

public interface CommandAI {
    boolean getParam(String param);
    void action(Field field, Robot robot);
}
