package com.vi.dsp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day5 {

    public void pageOrderFinder(List<int[]> refData, List<List<Integer>> testData){

        Map<Integer, List<Integer>> startNoToNextValuesMap = refData.stream().collect(Collectors.toMap(
                entry -> entry[0],
                entry -> new ArrayList<>(List.of(entry[1])),
                (existingList, newList) -> {
                    existingList.addAll(newList);
                    return existingList;
                })
            );
        List<List<Integer>> validValues = new ArrayList<>();
        ROW: for(List<Integer> rowData: testData){
           ELEMENTS: for(int i=0; i< rowData.size(); i++){
                if(i == rowData.size()-1) continue;
                int numToValidate = rowData.get(i);
                List<Integer> numAfterNumToValidate = rowData.subList(i+1, rowData.size());
                try {
                    boolean resp = new HashSet<>(startNoToNextValuesMap.get(numToValidate)).containsAll(numAfterNumToValidate);
                    if(!resp) break;
                    if(i == rowData.size()-2) validValues.add(rowData);
                }
                catch(NullPointerException e){
                    break;
                }
            }
        }
        Integer results = validValues.stream().map(item -> item.get(item.size()/2)).reduce(0, Integer::sum);;
        System.out.println(results);
    }

}
