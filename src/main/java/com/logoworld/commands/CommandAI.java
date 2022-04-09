package com.logoworld.commands;

import com.logoworld.environment.Field;
import com.logoworld.environment.Robot;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.BadParam;
import com.logoworld.exceptions.NotInitSurface;

public interface CommandAI {
    void getParam(String param) throws BadParam;
    void action(Field field, Robot robot) throws NotInitSurface, BadCoordinates;
    void action(Field field, Robot robot, String param) throws BadParam, NotInitSurface, BadCoordinates;
    void clone(CommandAI commandAI) throws CloneNotSupportedException, BadParam;
}
