package com.logoworld.commands;

import com.logoworld.environment.Field;
import com.logoworld.environment.Robot;
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
    }

    @Test
    @DisplayName("Testing of action(Filed, Robor, String)")
    void action() {
        Field field = new Field();
        Robot robot = new Robot();
    }
}