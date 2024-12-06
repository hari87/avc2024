package com.vi.dsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    String regex = "ul\\(\\d{1,3},\\d{1,3}";
    Pattern pattern = Pattern.compile(regex);
    List<Integer> multiplicationObjects = new ArrayList<>();
    public void readValidChars(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) >0 ) {
            if(buffer[0] == 109){
                inputStream.mark(10);
                String s = new String(inputStream.readNBytes(10));
                OverLoadedReturn response = containsClosingBracket(s);
                if(response.result) multiplicationObjects.add(response.multipliedValue);
                inputStream.reset();
            }
            baos.write(buffer, 0, bytesRead);
        }

        Integer sum = multiplicationObjects.stream().mapToInt(i -> i).sum();
        System.out.println(sum);
    }
    private OverLoadedReturn containsClosingBracket(String s){
        String[] splitted = s.split("\\)");
        Matcher matcher = pattern.matcher(splitted[0]);

        if(matcher.matches()){
            String[] numbers = splitted[0].split("\\(")[1].split(",");
            int mulValue = Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
            return new OverLoadedReturn(mulValue, matcher.matches());
        }
        return new OverLoadedReturn(0, matcher.matches());
    }
    public record OverLoadedReturn (Integer multipliedValue, boolean result){}
}
