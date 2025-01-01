package com.vi.dsp;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Day4Test {

    Day4 day4 = new Day4();
    @Test
    public void test() throws IOException {
        URL day4Url = Day4Test.class.getClassLoader().getResource("Day4Input.txt");
        BufferedReader br = new BufferedReader(new FileReader(day4Url.getFile()));
        List<String[]> arrayLines = new ArrayList<>();
        while (br.ready()) {
            String line = br.readLine();
            String[] arr = line.split("");
            arrayLines.add(arr);
        }
        day4.findXmas(arrayLines);

    }
}
