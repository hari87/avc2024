package com.vi.dsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {

    public void safeUnsafeReport(List<String> rawInputData){

        List<List<Integer>> inputData = rawInputData.stream()
                .map(item -> Arrays.stream(item.split(" "))
                        .map(Integer::parseInt)
                        .toList())
                .toList();

        int safeReportsCount = 0;
        for (String report : rawInputData) {
            List<Integer> levels = Arrays.stream(report.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            if (isSafe(levels) || canBeMadeSafe(levels)) {
                safeReportsCount++;
            }
        }
        System.out.println(safeReportsCount);

    }

    private static boolean isSafe(List<Integer> levels) {
        return isStrictlyIncreasing(levels) || isStrictlyDecreasing(levels);
    }

    // Check if removing one level makes the report safe
    private static boolean canBeMadeSafe(List<Integer> levels) {
        for (int i = 0; i < levels.size(); i++) {
            List<Integer> modified = new ArrayList<>(levels);
            modified.remove(i); // Remove the level at index i
            if (isSafe(modified)) {
                return true;
            }
        }
        return false;
    }

    // Check if the list is strictly increasing with a step of 1 to 3
    private static boolean isStrictlyIncreasing(List<Integer> levels) {
        for (int i = 1; i < levels.size(); i++) {
            int diff = levels.get(i) - levels.get(i - 1);
            if (diff < 1 || diff > 3) {
                return false;
            }
        }
        return true;
    }

    // Check if the list is strictly decreasing with a step of 1 to 3
    private static boolean isStrictlyDecreasing(List<Integer> levels) {
        for (int i = 1; i < levels.size(); i++) {
            int diff = levels.get(i - 1) - levels.get(i);
            if (diff < 1 || diff > 3) {
                return false;
            }
        }
        return true;
    }

    public void safeUnsafeReportBkp(List<String> rawInputData){

        List<List<Integer>> inputData = rawInputData.stream()
                .map(item -> Arrays.stream(item.split(" "))
                        .map(Integer::parseInt)
                        .toList())
                .toList();

        int safeCounter = 0;
        for (int i = 0; i < inputData.size(); i++){
            List<Integer> singleRow = inputData.get(i);
            int baseVal = singleRow.get(0);
            boolean isAscending = true;
            for (int j = 1; j < singleRow.size(); j++){
                int currentVal = singleRow.get(j);
                int diff = currentVal - baseVal;
                if(diff ==0) break;
                if(diff < 0){
                    if(j == 1) isAscending = false;
                    if(j != 1 && isAscending){
                        break;
                    }
                    diff = diff* -1;
                }
                else{
                    if(j !=1 && !isAscending) break;
                }
                if(diff > 0 && diff < 4){
                    baseVal = currentVal;
                    if(j == singleRow.size()-1) {
                        ++safeCounter;
                    }
                }
                else{
                    break;
                }
            }
        }
        System.out.println(safeCounter);

    }



}
