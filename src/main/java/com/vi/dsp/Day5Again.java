package com.vi.dsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day5Again {

    private Map<Integer, List<Integer>> getValueAheadOfKeyMap(List<int[]> refData){
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

    Map<Integer, List<Integer>> getValuesBehindKeyMap(List<int[]> refData) {
        return refData.stream()
        .collect(Collectors.toMap(
            item -> item[1],
            item -> new ArrayList<>(List.of(item[0])),
            (existingList, newList) -> {
                existingList.addAll(newList);
                return existingList;
            }
            ));
    }

    List<List<Integer>> validateTestDataElementHasRightSequenceAhead(Map<Integer, List<Integer>> valuesAfterKeyMap, List<List<Integer>> testData) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> testRow : testData) {
            for (Integer testElement : testRow) {
                List<Integer> subListToBeChecked = testRow.subList(testRow.indexOf(testElement) + 1, testRow.size());
                List<Integer> referenceListForGivenElement = valuesAfterKeyMap.get(testElement);
                boolean allSubListElementAreInRefList = referenceListForGivenElement.containsAll(subListToBeChecked);
                if (!allSubListElementAreInRefList) {
                    break;
                }
                if (testRow.indexOf(testElement) == testRow.size() - 1) result.add(testRow);
            }
        }
        return result;
    }

    List<List<Integer>> sequenceAheadWithAlteration(Map<Integer, List<Integer>> valuesAfterKeyMap, List<List<Integer>> testData) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> testDataSet = new ArrayList<>(testData);
        boolean isRowValidatedCompletely = false;
        for(int i=0; i< testDataSet.size(); i++) {
            List<Integer> testRow = new ArrayList<>(testDataSet.get(i));
            for (int j=0; j< testRow.size(); j++) {
                Integer testElement = testRow.get(j);
                List<Integer> subListToBeChecked = testRow.subList(testRow.indexOf(testElement) + 1, testRow.size());
                List<Integer> referenceListForGivenElement = valuesAfterKeyMap.get(testElement);
                Optional<Integer> misplacedElement =
                        subListToBeChecked.stream().filter(element -> !referenceListForGivenElement.contains(element)).findFirst();
                if(misplacedElement.isPresent()) {
                    int indexOfCurrentElement = testRow.indexOf(testElement);
                    testRow.remove(misplacedElement.get());
                    testRow.add(indexOfCurrentElement, misplacedElement.get());
                    testDataSet.set(i, testRow);
                    i--;
                    break;
                }
                else{
                    if(testRow.size() == j+1){
                        isRowValidatedCompletely = true;
                    }
                }
            }
            if(isRowValidatedCompletely){
                result.add(testRow);
                var checkRes = validateTestDataElementHasRightSequenceAhead(valuesAfterKeyMap, List.of(testRow));
                isRowValidatedCompletely = false;
            }
        }
        return result;
    }

    void getMiddleNumberAndAdd(List<List<Integer>> validatedTestResult){
        AtomicInteger outcome = new AtomicInteger();
        validatedTestResult.stream().
                forEach(row -> {
                    int middleIndex = row.size()/2;
                    outcome.addAndGet(row.get(middleIndex));
                });
        log.info("Final outcome is {}", outcome.get());
    }



    

    public void deduce(Day5Again day5Again, List<int[]> refData, List<List<Integer>> testData) {
        Map<Integer, List<Integer>> valuesAfterKeyMap = day5Again.getValueAheadOfKeyMap(refData);
        Map<Integer, List<Integer>> valuesBehindKeyMap = day5Again.getValuesBehindKeyMap(refData);
        List<List<Integer>> validatedTestDataAhead = day5Again.validateTestDataElementHasRightSequenceAhead(valuesAfterKeyMap, testData);
        getMiddleNumberAndAdd(validatedTestDataAhead);
        testData.removeAll(validatedTestDataAhead);
        List<List<Integer>> rightlySortedForIncorrectList = sequenceAheadWithAlteration(valuesAfterKeyMap,testData);
        getMiddleNumberAndAdd(rightlySortedForIncorrectList);
    }

}
