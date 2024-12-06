package com.vi.dsp;

import org.junit.jupiter.api.Test;

import java.util.List;

public class Day2Test {
    Day2 d = new Day2();

    @Test
    public void safeUnsafeReports() {
        String fileName = "Day2Input.txt";
        List<String> inputData = ReadFromResources.readFromResourceFile(fileName);
        d.safeUnsafeReport(inputData);
    }
}
