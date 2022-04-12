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

class TeleportTest {
    Teleport obj;
    String param;

    @BeforeEach
    void setUp(){
        obj = new Teleport();
    }

    @Test
    @DisplayName("Testing getParam method with different")
    void getParam() {
        param = null;
        try {
            obj.getParam(param);
            fail();
        } catch (BadParam e) {
            assertNotNull(e.getMessage());
        }

        param = "90 9 9";
        try {
            obj.getParam(param);
            fail();
        } catch (BadParam e) {
            assertNotNull(e.getMessage());
        }

        param = "90 titatu";
        try {
            obj.getParam(param);
            fail();
        } catch (BadParam e) {
            assertNotNull(e.getMessage());
        }

        param = "Some text there.";
        try {
            obj.getParam(param);
            fail();
        } catch (BadParam e) {
            assertNotNull(e.getMessage());
        }

        param = "09 09";
        try {
            obj.getParam(param);
            fail();
        } catch (BadParam e) {
            assertNotNull(e.getMessage());
        }

        param = "90 90";
        try {
            obj.getParam(param);
            assertNotNull("Parameters got successfully");
        } catch (BadParam e) {
            fail(e.getMessage());
        }

        param = "90    90";
        try {
            obj.getParam(param);
            assertNotNull("Parameters got successfully");
        } catch (BadParam e) {
            fail(e.getMessage());
        }
    }

    @Test
    void action() throws NotInitSurface {
        Field field = new Field();
        Robot robot = new Robot();

        param = "90 90";
        try {
            field.setDisplayedSurface(10, 10, robot);
            obj.action(field, robot, param);
            fail();
        } catch (InterruptedException | BadParam | NotInitSurface e) {
            fail(e.getMessage());
        } catch (BadCoordinates e) {
            assertNotNull(e.getMessage());
        }

        param = "9 9";
        try {
            field.setDisplayedSurface(10, 10, robot);
            obj.action(field, robot, param);
            assertNotNull("Correct param");
        } catch (InterruptedException | BadCoordinates | BadParam | NotInitSurface e) {
            fail(e.getMessage());
        }

        param = "9 9";
        try {
            field.setDisplayedSurface(10, 10, robot);
            obj.action(null, null, param);
            fail();
        } catch (InterruptedException | BadCoordinates | BadParam e) {
            fail(e.getMessage());
        } catch (NotInitSurface e) {
            assertNotNull("Null field and robot");
        }

        param = "09 0 9";
        try {
            obj.action(null, null, param);
        } catch (BadCoordinates | NotInitSurface e) {
            fail(e.getMessage());
        } catch (BadParam e) {
            assertNotNull("Bad param test");
        }
    }

}