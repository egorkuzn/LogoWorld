package com.logoworld.commands;

import com.logoworld.environment.Field;
import com.logoworld.environment.Robot;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.BadParam;
import com.logoworld.exceptions.NotInitSurface;

/**
 * Base class for all commands
 */
public interface CommandAI {
    /**
     * Args getting, preparing and checking.
     * @param param options, that specific for every command
     * @throws BadParam if no parameters responsibility
     */
    void getParam(String param) throws BadParam;
    /**
     * Some specific method for each command. Obviously, that command.
     * @param field where
     * @param robot who
     * @throws NotInitSurface problems with {@code field} or {@code robot} initialization
     * @throws BadCoordinates uncorrected {@code robot} position on {@code field}
     */
    void action(Field field, Robot robot) throws NotInitSurface, BadCoordinates;

    /**
     * This meth is running like {@code getPram(String) + action(Field, Robot)}
     */
    void action(Field field, Robot robot, String param) throws BadParam, NotInitSurface, BadCoordinates;

    /**
     * Meth for future operations with command. Coping from one to another.
     * @param commandAI what to copy
     * @throws CloneNotSupportedException if coping impossible
     * @throws BadParam if param from other and this object incomparable
     */
    void clone(CommandAI commandAI) throws CloneNotSupportedException, BadParam;
}
