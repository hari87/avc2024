package com.vi.dsp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.log4j.Log4j2;

@Log4j2
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
        LinkedList<Integer> sortedValues = orderItems(refData);
        List<List<Integer>> validValues = new ArrayList<>();
        List<List<Integer>> inValidValues = new ArrayList<>();
        ROW: for(List<Integer> rowData: testData){
           ELEMENTS: for(int i=0; i< rowData.size(); i++){
                if(i == rowData.size()-1) continue;
                int numToValidate = rowData.get(i);
                List<Integer> numAfterNumToValidate = rowData.subList(i+1, rowData.size());
                try {
                    boolean resp = new HashSet<>(startNoToNextValuesMap.get(numToValidate)).containsAll(numAfterNumToValidate);
                    if(!resp) {
                        inValidValues.add(extracted(rowData, sortedValues));
                        break;
                    }
                    if(i == rowData.size()-2) validValues.add(rowData);
                }
                catch(NullPointerException e){
                    inValidValues.add(extracted(rowData, sortedValues));
                    break;
                }
            }
        }
        Integer validResult = validValues.stream().map(item -> item.get(item.size()/2)).reduce(0, Integer::sum);;
        System.out.println("The value of valid result is : " + validResult);

        Integer inValidResult = inValidValues.stream().map(item -> item.get(item.size()/2)).reduce(0, Integer::sum);;
        System.out.println("The value of invalid result is : " + inValidResult);
    }

    private List<Integer> extracted(List<Integer> rowData, LinkedList<Integer> sortedValues) {
        return sortIncorrectElements(rowData, sortedValues);
    }

    private List<Integer> sortIncorrectElements(List<Integer> elements, LinkedList<Integer> baseSortedList) {
        Map<Integer, Integer> indexToValueMap = elements.stream().collect(Collectors.toMap(baseSortedList::indexOf, item -> item));
        Map<Integer, Integer> sortedMap = indexToValueMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sort by key
                .collect(Collectors.toMap(
                        Map.Entry::getKey,   // Key mapper
                        Map.Entry::getValue, // Value mapper
                        (existing, ignored) -> existing, // Merge function required as defensive approach.
                        LinkedHashMap::new   // Use LinkedHashMap to preserve order
                ));
        return sortedMap.values().stream().toList();
    }

    private LinkedList<Integer> orderItems(List<int[]> data){
        LinkedList<Integer> orderedList = new LinkedList<>();

        for (int[] pairInScope : data) {
            if (!orderedList.contains(pairInScope[0])) orderedList.add(pairInScope[0]);
            if (!orderedList.contains(pairInScope[1])) orderedList.add(pairInScope[1]);
        }

        for (int[] pairInScope : data) {
            int indexOfPredecessor = orderedList.indexOf(pairInScope[0]);
            int indexOfSuccessor = orderedList.indexOf(pairInScope[1]);
            if ((indexOfPredecessor > indexOfSuccessor)) {
                orderedList.remove(indexOfPredecessor);
                if(indexOfSuccessor != 0){
                    orderedList.add(indexOfSuccessor , pairInScope[0]);
                }
                else{
                    orderedList.addFirst(pairInScope[0]);
                }

            }
        }

        return orderedList;
    }

    void orderNumber(Map<Integer, List<Integer>> refDataMap) {
        Map<Integer, Integer> sizeOfObjects = refDataMap.entrySet().stream().collect(Collectors.toMap(
        entry -> entry.getKey(),
        entry -> entry.getValue().size()
        ));
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(sizeOfObjects.entrySet());
        entryList.sort(Map.Entry.<Integer, Integer>comparingByValue().reversed());
        List<Integer> elementSequence = new ArrayList<>(entryList.stream().map(entry -> entry.getKey()).toList());
        int lastElement = refDataMap.get(elementSequence.getLast()).get(0);
        elementSequence.addLast(lastElement);
        log.info(elementSequence);
    }

}
