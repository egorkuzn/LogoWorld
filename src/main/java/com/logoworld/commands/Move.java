package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;

public class Move implements CommandAI{
    char way;
    @Override
    public boolean getParam(String param) {
        switch (param){
            case "L":
                way = 'L';
                break;
            case "R":
                way = 'R';
                break;
            case "U":
                way = 'U';
                break;
            case "D":
                way = 'D';
                break;
            default:
                return false;
        }

        return true;
    }

    @Override
    public void action(Field field, Robot robot) {

    }
}
