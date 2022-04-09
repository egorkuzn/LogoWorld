package com.logoworld.commands;

import com.logoworld.environment.Field;
import com.logoworld.environment.Robot;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.NotInitSurface;

public interface CommandAI {
    void getParam(String param);
    boolean runParam(String param);
    void action(Field field, Robot robot) throws NotInitSurface, BadCoordinates;
    void clone(CommandAI commandAI) throws CloneNotSupportedException;
}
