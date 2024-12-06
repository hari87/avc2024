package com.vi.dsp;

import org.junit.jupiter.api.Test;

import java.util.List;

public class Day1Test {
    Day1 day1Test = new Day1();
    @Test
    public void totalDistanceBetweenListsTest() {
        String fileName = "Day1Input.txt";
        List<String> inputData = ReadFromResources.readFromResourceFile(fileName);
        day1Test.totalDistanceBetweenLists(inputData);
    }

    @Test
    public void similarityScoreTest() {
        String fileName = "Day1Input_Part2.txt";
        List<String> inputData = ReadFromResources.readFromResourceFile(fileName);
        day1Test.similarityScore(inputData);
    }
}
