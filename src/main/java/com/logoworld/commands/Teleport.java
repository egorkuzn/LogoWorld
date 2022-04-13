package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.BadParam;
import com.logoworld.exceptions.NotInitSurface;

import java.util.logging.Logger;

/**
 * This command sets robot (x, y) coordinates on field
 */

public class Teleport implements CommandAI{
    private int x;
    private int y;
    public String param = null;
    private static final Logger log = Logger.getLogger(Teleport.class.getName());

    /**
     * String to int conversation.
     * @param str argument of parameter
     * @return integer get from String str
     */
    private int convert(String str)
    {
        int value = 0;
        log.info("String = " + str);

        // Convert the String
        try {
            value = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {

            // This is thrown when the String
            // contains characters other than digits
            log.info("Invalid String");
        }
        return value;
    }

    /**
     * Getting parameters for the field initialization: height and width of surface
     * and robot start position
     * @param param options, that specific for every command
     * @throws BadParam if param not contains 4 not negative integers
     */
    @Override
    public void getParam(String param) throws BadParam {
        if(param == null || !param.matches("^0\\s+0$")
                && !param.matches("^0\\s+[1-9]\\d*$")
                && !param.matches("^[1-9]\\d*\\s+[1-9]\\d*$")
                && !param.matches("^[1-9]\\d*\\s+0$")) {
            log.info("Bad param: " + param);
            throw new BadParam("TELEPORT");
        }

        String[] arr = param.split("\\s+");

        x = convert(arr[0]);
        y = convert(arr[1]);

        log.info("Args successfully got: (" + x + ", " + y + ")");
    }

    /**
     * Teleportation of robot in some concrete (x,y) point
     * @param field where
     * @param robot who
     * @throws BadCoordinates if parameters string had uncorrected arguments
     * @throws NotInitSurface some exception in way of environment initializations
     */
    @Override
    public void action(Field field, Robot robot) throws BadCoordinates, NotInitSurface {
        if(field == null || !field.isInited()) {
            log.info("No INIT: null field");
            throw new NotInitSurface("no inited", "TELEPORT");
        } else if(robot == null) {
            log.info("No Init: null robot");
            throw new NotInitSurface("null surface of Field", "TELEPORT");
        }

        log.info("Robot coordinates set: (" + x + ", " + y + ")");
        field.hideRobot(robot);

        if(!robot.setCoordinates(x, y)) {
            log.info("Null surface or bad coordinates of surface");
            throw new BadCoordinates(robot.X(), robot.Y(), "TELEPORT");
        }

        if(!field.displayRobot(robot)) {
            log.info("Null surface or bad coordinates of surface");
            throw new NotInitSurface("null surface of Field", "TELEPORT");
        }

        log.info("Successful INIT");
    }

    /**
     * {@code getParam() + action()}
     */
    @Override
    public void action(Field field, Robot robot, String param) throws BadParam, NotInitSurface, BadCoordinates {
        getParam(param);
        action(field, robot);
    }

    /**
     * Non-used function for future work with commands
     * @param commandAI what to copy
     * @throws CloneNotSupportedException what if copy impossible
     * @throws BadParam if comparable param types
     */
    @Override
    public void clone(CommandAI commandAI) throws CloneNotSupportedException, BadParam {
        if(commandAI.getClass() == this.getClass()){
            this.param = ((Teleport) commandAI).param;
            getParam(this.param);
        } else
            throw new CloneNotSupportedException();
    }
}
