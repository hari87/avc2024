package com.vi.dsp;

import java.util.List;

public class Day4 {
    public void findCrossMas(List<String[]> inputData){

        int hitCount = 0;
        ROW: for(int i = 0; i < inputData.size(); i++) {
            COLUMN:
            for (int j = 0; j < inputData.get(i).length; j++) {
                if(inputData.get(i)[j].equals("A")){
                    if(checkCrossedMas(i, j, inputData)) hitCount++;
                }
            }
        }
        System.out.println(hitCount);
    }
    public void findXmas(List<String[]> inputData){
        int hitCount = 0;
        ROW: for(int i = 0; i < inputData.size(); i++){
        COLUMN: for(int j = 0; j < inputData.get(i).length; j++){
                    if(inputData.get(i)[j].equals("X")){
                        if(checkNorth(i, j, inputData)) hitCount++;
                        if(checkSouth(i, j, inputData)) hitCount++;
                        if(checkEast(i, j, inputData)) hitCount++;
                        if(checkWest(i, j, inputData)) hitCount++;
                        if (checkSouthWest(i, j, inputData)) hitCount++;
                        if(checkSouthEast(i, j, inputData)) hitCount++;
                        if (checkNorthEast(i, j, inputData)) hitCount++;
                        if (checkNorthWest(i, j, inputData)) hitCount++;
                    }
            }
        }
        System.out.println(hitCount);
    }

    boolean checkCrossedMas(int rowPosition, int colPosition, List<String[]> inputData){
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        if(colPosition < 1 || colPosition > inputData.getFirst().length-2 || rowPosition < 1 || rowPosition > inputData.size()-2) return false;
        sb1.append(inputData.get(rowPosition-1)[colPosition-1]);
        sb1.append(inputData.get(rowPosition+1)[colPosition+1]);
        sb2.append(inputData.get(rowPosition-1)[colPosition+1]);
        sb2.append(inputData.get(rowPosition+1)[colPosition-1]);

        String result1 = sb1.toString();
        String result2 = sb2.toString();
        boolean result1Response = result1.contains("MS") || result1.contains("SM");
        boolean result2Response = result2.contains("MS") || result2.contains("SM");
        return (result1Response && result2Response);
    }

    boolean checkSouthWestForMas(int rowPosition, int colPosition, List<String[]> inputData){
        StringBuilder sb = new StringBuilder();
        if(rowPosition > inputData.size()-3 || colPosition < 2) return false;
        for(int i =0;  i< 3; i++){
            sb.append(inputData.get(rowPosition+i)[colPosition-i]);
        }
        String result = sb.toString();
        return (result.contains("MAS") || result.contains("SAM"));
    }

    boolean checkNorthEastForMas(int rowPosition, int colPosition, List<String[]> inputData){
        StringBuilder sb = new StringBuilder();
        if(rowPosition < 2 || colPosition > inputData.getFirst().length-3) return false;
        for(int i =0;  i< 3; i++){
            sb.append(inputData.get(rowPosition-i)[colPosition+i]);
        }
        String result = sb.toString();
        return (result.contains("MAS") || result.contains("SAM"));
    }
    boolean checkNorthWestForMas(int rowPosition, int colPosition, List<String[]> inputData){
        StringBuilder sb = new StringBuilder();
        if(rowPosition < 2 || colPosition < 2) return false;
        for(int i =0;  i< 3; i++){
            sb.append(inputData.get(rowPosition-i)[colPosition-i]);
        }
        String result = sb.toString();
        return (result.contains("MAS") || result.contains("SAM"));
    }

    boolean checkNorth(int rowPosition, int colPosition, List<String[]> inputData){
        StringBuilder sb = new StringBuilder();
        if(rowPosition < 3) return false;
        for(int i = rowPosition; i >= rowPosition-2; i--){
            sb.append(inputData.get(i-1)[colPosition]);
        }
        return sb.toString().equals("MAS");
    }
    boolean checkSouth(int rowPosition, int colPosition, List<String[]> inputData){
        StringBuilder sb = new StringBuilder();
        if(rowPosition > inputData.size()-4) return false;
        for(int i = rowPosition+1; i < rowPosition+4; i++){
            sb.append(inputData.get(i)[colPosition]);
        }
        return sb.toString().equals("MAS");
    }

    boolean checkEast(int rowPosition, int colPosition, List<String[]> inputData){
        StringBuilder sb = new StringBuilder();
        if(colPosition > inputData.getFirst().length-4) return false;
        for(int i = colPosition+1; i < colPosition+4; i++){
            sb.append(inputData.get(rowPosition)[i]);
        }
        return sb.toString().equals("MAS");
    }

    boolean checkWest(int rowPosition, int colPosition, List<String[]> inputData){
        StringBuilder sb = new StringBuilder();
        if(colPosition < 3) return false;
        for(int i = colPosition-1; i >= colPosition-3; i--){
            sb.append(inputData.get(rowPosition)[i]);
        }
        return sb.toString().equals("MAS");
    }

    boolean checkSouthEast(int rowPosition, int colPosition, List<String[]> inputData){
        StringBuilder sb = new StringBuilder();
        if(colPosition > inputData.getLast().length-4 || rowPosition > inputData.size()-4) return false;
        for(int i =1;  i< 4; i++){
            sb.append(inputData.get(rowPosition+i)[colPosition+i]);
        }
        return sb.toString().equals("MAS");
    }

    boolean checkSouthWest(int rowPosition, int colPosition, List<String[]> inputData){
        StringBuilder sb = new StringBuilder();
        if(rowPosition > inputData.size()-4 || colPosition < 3) return false;
        for(int i =1;  i< 4; i++){
            sb.append(inputData.get(rowPosition+i)[colPosition-i]);
        }
        return sb.toString().equals("MAS");
    }

    boolean checkNorthEast(int rowPosition, int colPosition, List<String[]> inputData){
        StringBuilder sb = new StringBuilder();
        if(rowPosition < 3 || colPosition > inputData.getFirst().length-4) return false;
        for(int i =1;  i< 4; i++){
            sb.append(inputData.get(rowPosition-i)[colPosition+i]);
        }
        return sb.toString().equals("MAS");
    }
    boolean checkNorthWest(int rowPosition, int colPosition, List<String[]> inputData){
        StringBuilder sb = new StringBuilder();
        if(rowPosition < 3 || colPosition < 3) return false;
        for(int i =1;  i< 4; i++){
            sb.append(inputData.get(rowPosition-i)[colPosition-i]);
        }
        return sb.toString().equals("MAS");
    }

}
