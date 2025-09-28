package com.vi.dsp;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5Test {
    Day5 day5 = new Day5();
    @Test
    public void test() throws IOException {
        URL day5RefUrl = Day5Test.class.getClassLoader().getResource("Day5RefInput.txt");
        URL day5TestInputUrl = Day5Test.class.getClassLoader().getResource("Day5TestInput.txt");

        BufferedReader br = new BufferedReader(new FileReader(day5RefUrl.getFile()));
        List<int[]> refData = new ArrayList<>();
        while (br.ready()) {
            String line = br.readLine();
            String[] arr = line.split("\\|");
            int[] numbers = new int[2];
            numbers[0] = Integer.parseInt(arr[0]);
            numbers[1] = Integer.parseInt(arr[1]);
            refData.add(numbers);
        }
        br.close();
        br = new BufferedReader(new FileReader(day5TestInputUrl.getFile()));
        List<List<Integer>> testData = new ArrayList<>();
        while (br.ready()) {
            String line = br.readLine();
            String[] arr = line.split(",");
            List<Integer> nums = Arrays.stream(arr).mapToInt(Integer::parseInt).boxed().toList();
            testData.add(nums);
        }

        //day5.pageOrderFinder(refData, testData);
        Day5Again day5Again = new Day5Again();
        
        day5Again.deduce(day5Again, refData, testData);

    }


}
