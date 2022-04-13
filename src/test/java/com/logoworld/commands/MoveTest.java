package com.logoworld.commands;

import com.logoworld.exceptions.BadParam;
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

    }
}