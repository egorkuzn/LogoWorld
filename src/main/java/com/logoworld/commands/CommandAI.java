package com.logoworld.commands;

import com.logoworld.environment.Field;
import com.logoworld.environment.Robot;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.NotInitSurface;

public interface CommandAI {
    boolean getParam(String param);
    void action(Field field, Robot robot) throws NotInitSurface, BadCoordinates;
}
