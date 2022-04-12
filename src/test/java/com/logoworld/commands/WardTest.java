package com.logoworld.commands;

import com.logoworld.environment.*;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.BadParam;
import com.logoworld.exceptions.NotInitSurface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WardTest {
    Ward obj;
    String param;

    @BeforeEach
    void setUp(){
        obj = new Ward();
    }

    @Test
    @DisplayName("Testing Ward with some arguments")
    public void notNullParam(){
        param = "Some test text";
        try {
            obj.getParam(param);
            fail();
        } catch (BadParam e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing Ward with null Robot and Field")
    public void nullActionArgs(){
        try {
            obj.action(null, null);
            fail();
        } catch (NotInitSurface e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing Ward with correct param")
    public void correctParam(){
        param = null;
        try {
            obj.getParam(param);
            Field field = new Field();
            Robot robot = new Robot();

            field.setDisplayedSurface(5,5,robot);

            obj.action(field, robot, param);
            assertNotNull("Action made succefully");
        } catch (BadParam | BadCoordinates | NotInitSurface | InterruptedException e) {
            fail(e.getMessage());
        }
    }
}