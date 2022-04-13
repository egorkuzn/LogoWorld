package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadParam;
import com.logoworld.exceptions.NotInitSurface;
import java.util.logging.Logger;

/**
 * This is the command sets draw mode on robot.
 * */

public class Draw implements CommandAI{
    private static final Logger log = Logger.getLogger(Draw.class.getName());

    /**
     * {@code getParam} method sets parameters for the next action.
     * @param param  should be {@code null}, else it would be exception
     * @throws BadParam
     */
    @Override
    public void getParam(String param) throws BadParam{
        if(param != null) {
            log.info("Param getting: not null");
            throw new BadParam("DRAW");
        }

        log.info("Param getting: good");
    }

    /**
     * {@code action} method takes args, that was prepared in getParam. So robot starts draw after that.
     * @param field where you are planning to run
     * @param robot who starting drawing
     * @throws NotInitSurface
     */
    @Override
    public void action(Field field, Robot robot) throws NotInitSurface {
        log.info("Taking action");
        if(field == null || !field.isInited()) {
            log.info("Bad field obj");
            throw new NotInitSurface("no inited", "DRAW");
        }
        if(robot == null) {
            log.info("NULL Robot obj");
            throw new NotInitSurface("robot object is NULL", "DRAW");
        }

        robot.setDrawerStatus(true);
        log.info("Drawer mode set up finished");
    }

    /**
     * Super function which combines as {@code getParam} as {@code action}
     * @param field where
     * @param robot who
     * @param param should be {@code null}
     * @throws BadParam if problems with {@code getParam} func
     * @throws NotInitSurface if problems with {@code robot} or {@code field}
     */
    @Override
    public void action(Field field, Robot robot, String param) throws BadParam, NotInitSurface {
        getParam(param);
        action(field, robot);
    }

    /**
     * Non-used function for future work with commands
     * @param commandAI what to copy
     * @throws CloneNotSupportedException what if copy impossible
     */
    @Override
    public void clone(CommandAI commandAI) throws CloneNotSupportedException {
        if(commandAI.getClass() == this.getClass()){
            return;
        }else
            throw new CloneNotSupportedException();
    }
}
