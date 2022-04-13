package com.logoworld.commands;

import com.logoworld.environment.Field;
import com.logoworld.environment.Robot;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.BadParam;
import com.logoworld.exceptions.NotInitSurface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitTest {
    Init obj;
    String param;

    @BeforeEach
    void setUp() {
        obj = new Init();
    }

    @Test
    @DisplayName("Testing of getParam method of INIT")
    void getParam() {
        param = null;
        try{
            obj.getParam(param);
            fail();
        } catch (BadParam e) {
            assertNotNull(e.getMessage());
        }

        param = "58 60 35 20";
        try{
            obj.getParam(param);
            assertNotNull("Good param");
        } catch (BadParam e) {
            fail(e.getMessage());
        }

        param = "0 0 0 0";
        try{
            obj.getParam(param);
            fail();
        } catch (BadParam e) {
            assertNotNull(e.getMessage());
        }

        param = "8  9 -1 6";
        try{
            obj.getParam(param);
            fail();
        } catch (BadParam e) {
            assertNotNull(e.getMessage());
        }

        param = "08 6 5 1";
        try{
            obj.getParam(param);
            fail();
        } catch (BadParam e) {
            assertNotNull(param);
        }

        param = "8 6 10 3";
        try{
            obj.getParam(param);
            fail();
        } catch (BadParam e){
           assertNotNull(e.getMessage());
        }

        param = "        ";
        try {
            obj.getParam(param);
            fail();
        } catch (BadParam e) {
            assertNotNull(e.getMessage());
        }

    }

    @Test
    @DisplayName("Testing of action(Filed, Robot, String)")
    void action() {
        Field field = new Field();
        Robot robot = new Robot();

        param = "10 10 9 9";
        try{
            field.setDisplayedSurface(10, 10, robot);
            obj.action(field, robot, param);
            assertNotNull("Good param");
        } catch (BadCoordinates | BadParam | InterruptedException | NotInitSurface e) {
            fail(e.getMessage());
        }

        param = "85 85 0 0";
        try{
            obj.action(null, null, param);
            fail();
        } catch (BadCoordinates | BadParam e) {
            fail(e.getMessage());
        } catch (NotInitSurface e){
            assertNotNull(e.getMessage());
        }

        try{
            field.setDisplayedSurface(10, 10, robot);
            obj.action(field, null, param);
            fail();
        } catch (BadCoordinates | BadParam | InterruptedException e) {
            fail(e.getMessage());
        } catch (NotInitSurface e){
            assertNotNull(e.getMessage());
        }
    }
}