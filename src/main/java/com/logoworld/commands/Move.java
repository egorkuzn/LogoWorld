package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.BadParam;
import com.logoworld.exceptions.NotInitSurface;
import java.util.logging.Logger;

/**
 * Move to up, down, left, right on some count of steps
 */

public class Move implements CommandAI{
    private char way;
    private int steps;
    public String param;
    private static final Logger log = Logger.getLogger(Move.class.getName());

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
        if(param == null || !param.matches("^\\w+\\s+0$") && !param.matches("^\\w+\\s+[1-9]\\d*$")) {
            log.info("Bad param: " + param);
            throw new BadParam("MOVE");
        }

        this.param = param;
        String[] arr = param.split("\\s+");

        switch (arr[0]){
            case "L":
                log.info("Direction: left");
                way = 'L';
                break;
            case "R":
                log.info("Direction: right");
                way = 'R';
                break;
            case "U":
                log.info("Direction: left");
                way = 'U';
                break;
            case "D":
                log.info("Direction: down");
                way = 'D';
                break;
            default:
                log.info("Direction: unknown");
                throw new BadParam("MOVE :: bad symbol of direction");
        }

        log.info("Taking steps count ...");
        steps = Integer.parseInt(arr[1]);

        if(steps < 0) {
            log.info("Bad count of steps");
            throw new BadParam("MOVE :: negative count of steps");
        }

        log.info("Move direction and count of steps successfully got");
    }

    /**
     * Replacement of robot in some direction and some steps in that direction
     * @param field where
     * @param robot who
     * @throws BadCoordinates if parameters string had uncorrected arguments
     * @throws NotInitSurface some exception in way of environment initializations
     */
    @Override
    public void action(Field field, Robot robot) throws NotInitSurface, BadCoordinates {
        if(field == null || !field.isInited()){
            log.info("No INIT: null field");
            throw new NotInitSurface("no INITed", "MOVE");
        } else if(robot == null) {
            log.info("No Init: null robot");
            throw new NotInitSurface("null surface of Filed", "MOVE");
        }

        for(int i = 0; i < steps; ++i){
            field.hideRobot(robot);
            log.info("Number of the step: " + i);

            switch (way){
                case 'L':
                    log.info("Step left");
                    robot.moveLeft();
                    break;
                case 'R':
                    log.info("Step right");
                    robot.moveRight();
                    break;
                case 'U':
                    log.info("Step up");
                    robot.moveUp();
                    break;
                case 'D':
                    log.info("Step down");
                    robot.moveDown();
                    break;
            }

            if(!field.displayRobot(robot)) {
                log.info("Null surface or bad coordinates of surface");
                throw new NotInitSurface("null surface of Filed", "MOVE");
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        log.info("MOVE successfully complete");
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
            this.param = ((Move) commandAI).param;
            getParam(this.param);
        } else
                throw new CloneNotSupportedException();
    }
}
