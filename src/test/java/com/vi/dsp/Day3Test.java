package com.vi.dsp;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;

public class Day3Test {
    Day3 d = new Day3();
    @Test
    public void readValidChars() throws IOException {
        var fileName = "Day3Input.txt";
        InputStream is = ReadFromResources.class.getClassLoader().getResourceAsStream(fileName);
        d.readValidChars(is);

    }
    @Test
    public void anotherTest() throws IOException {
        d.test02();
    }
}
