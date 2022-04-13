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

class MoveTest {
    Move obj;
    String param;

    @BeforeEach
    void setUp(){
        obj = new Move();
    }

    @Test
    @DisplayName("Testing of getParam method of MOVE cmd")
    void getParam() {
        param = null;
        try{
            obj.getParam(param);
            fail();
        } catch (BadParam e) {
            assertNotNull(e.getMessage());
        }

        param = "90 9 9";
        try{
            obj.getParam(param);
            fail();
        } catch (BadParam e) {
            assertNotNull(e.getMessage());
        }

        param = "Some text there";
        try{
            obj.getParam(param);
            fail();
        } catch (BadParam e){
          assertNotNull(e.getMessage());
        }

        param = "L 90";
        try{
            obj.getParam(param);
            assertNotNull("Good param");
        } catch (BadParam e) {
            fail(e.getMessage());
        }

        param = "R                100";
        try {
            obj.getParam(param);
            assertNotNull("Good param");
        } catch (BadParam e){
            fail(e.getMessage());
        }

        param = "U 09";
        try{
            obj.getParam(param);
            fail();
        } catch (BadParam e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing of action(Field, Robot, String)")
    void action() {
        Field field = new Field();
        Robot robot = new Robot();

        param = "L 90";
        try {
            field.setDisplayedSurface(10, 10, robot);
            obj.action(field, robot, param);
            assertNotNull("Good param");
        } catch (InterruptedException | NotInitSurface | BadCoordinates | BadParam e) {
            fail(e.getMessage());
        }

        try {
            obj.action(null, null, param);
            fail();
        } catch (BadCoordinates | BadParam e) {
            fail(e.getMessage());
        } catch (NotInitSurface e) {
            assertNotNull(e.getMessage());
        }

        try {
            field.setDisplayedSurface(10, 6, robot);
            obj.action(field, null, param);
            fail();
        } catch (BadCoordinates | BadParam | InterruptedException e) {
            fail(e.getMessage());
        } catch (NotInitSurface e) {
            assertNotNull(e.getMessage());
        }

        param = "U 0";
        try {
            field.setDisplayedSurface(10, 10, robot);
            obj.action(field, robot, param);
            assertNotNull("Good param");
        } catch (InterruptedException | NotInitSurface | BadCoordinates | BadParam e) {
            fail(e.getMessage());
        }
    }
}