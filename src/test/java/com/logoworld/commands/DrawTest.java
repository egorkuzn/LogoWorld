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

class DrawTest {
    Draw obj;
    String param;

    @BeforeEach
    void setUp() {
        obj = new Draw();
    }

    @Test
    @DisplayName("Testing Draw getParam meth")
    void getParam() {
        param = "Some test text";
        try {
            obj.getParam(param);
            fail();
        } catch (BadParam e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing Draw action ")
    void action() {
        param = null;
        try {
            obj.getParam(param);
            Field field = new Field();
            Robot robot = new Robot();

            field.setDisplayedSurface(5,5,robot);

            obj.action(field, robot, param);
            assertNotNull("Action made succefully");
        } catch (BadParam | NotInitSurface | InterruptedException e) {
            fail(e.getMessage());
        }

        try {
            obj.action(null, null);
            fail();
        } catch (NotInitSurface e) {
            assertNotNull(e.getMessage());
        }
    }
}