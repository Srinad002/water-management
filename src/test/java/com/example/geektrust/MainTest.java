package com.example.geektrust;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@DisplayName("Integration Tests")
public class MainTest {

    private PrintStream standardOut = System.out;
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    private void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    private void tearDown() {
        System.setOut(standardOut);
    }

    @DisplayName("Integration test 1")
    @Test
    public void test1() {
        String expected = "3900 10334";
        Main.run("input.txt");
        Assertions.assertEquals(expected, outputStream.toString().trim());
    }
}