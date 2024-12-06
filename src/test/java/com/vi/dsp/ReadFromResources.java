package com.vi.dsp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFromResources {

    public static List<String> readFromResourceFile(String fileName) {
        List<String> lineList = new ArrayList<String>();
        try(InputStream is = ReadFromResources.class.getClassLoader().getResourceAsStream(fileName)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                lineList.add(line);
            }
            BufferedInputStream bis = new BufferedInputStream(ReadFromResources.class.getClassLoader().getResourceAsStream(fileName));

        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        return lineList;
    }
}
