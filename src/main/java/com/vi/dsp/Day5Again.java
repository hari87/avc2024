package com.vi.dsp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day5Again {

    private Map<Integer, List<Integer>> orderPageCorrectly(List<int[]> refData, List<List<Integer>> testData){
            return refData.stream()
                .collect(Collectors.toMap(
                    entry -> entry[0], 
                    entry -> new ArrayList<>(List.of(entry[1])),
                    (existingList, newList) -> {
                        existingList.addAll(newList);
                        return existingList;
                    }
                ));
                
    }

    private List<Integer> orderNumber(Map<Integer, List<Integer>> refDataMap) {
        log.info("for key: {}, values are {}");
        Map<Integer, Integer> sizeOfObjects = refDataMap.entrySet().stream().collect(Collectors.toMap(
        entry -> entry.getKey(),
        entry -> entry.getValue().size()
        ));
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(sizeOfObjects.entrySet());
        entryList.sort(Map.Entry.<Integer, Integer>comparingByValue().reversed());
        List<Integer> elementSequence = new ArrayList<>(entryList.stream().map(entry -> entry.getKey()).toList());
        int lastElement = refDataMap.get(elementSequence.getLast()).get(0);
        elementSequence.addLast(lastElement);
        return elementSequence;
    }

    boolean reOrederSequenceInTestDataRow(List<Integer> testRow, List<Integer> validSequence){
        Map<Integer, Integer> result = new LinkedHashMap();
        for(int i = 0; i< testRow.size(); i++){
            result.put(testRow.get(i), validSequence.indexOf(testRow.get(i)));
        }
        List<Integer> rowValuePrioList = result.values().stream().toList();
        
        return IntStream.range(1, rowValuePrioList.size())
                        .allMatch(i -> rowValuePrioList.get(i) >= rowValuePrioList.get(i - 1));
    }

    

    public void deduce(Day5Again day5Again, List<int[]> refData, List<List<Integer>> testData) {
        Map<Integer, List<Integer>> mappedReferenceData = day5Again.orderPageCorrectly(refData, testData);
        List<Integer> elementSequence = day5Again.orderNumber(mappedReferenceData);
        testData.forEach(row -> reOrederSequenceInTestDataRow(row, elementSequence));
        // List<List<Integer>> validResults = testData.stream().filter(row -> reOrederSequenceInTestDataRow(row, elementSequence)).toList();
        // List<List<Integer>> inValidResults = testData.stream().filter(row -> !reOrederSequenceInTestDataRow(row, elementSequence)).toList();
        //log.info("Valid List {}", validResults);
        //log.info("InValid List {}", inValidResults);
    }

}
