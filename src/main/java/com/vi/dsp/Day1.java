package com.vi.dsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day1 {

    public void totalDistanceBetweenLists(List<String> rawInputData){
        List<Integer> listOne = new ArrayList<>();
        List<Integer> listTwo = new ArrayList<>();
        int totalDistance = 0;
        prepareData(rawInputData, listOne, listTwo);
        // sort items in ascending order
        Collections.sort(listOne);
        Collections.sort(listTwo);
        for(int i = 0; i < listOne.size(); i++){
            int outcome = listOne.get(i) - listTwo.get(i);
            if(outcome < 0) outcome = outcome * -1;
            totalDistance += outcome;
        }
        System.out.println(totalDistance);

    }

    public void similarityScore(List<String> rawInputData){
        List<Integer> listOne = new ArrayList<>();
        List<Integer> listTwo = new ArrayList<>();
        prepareData(rawInputData, listOne, listTwo);

        Map<Integer, Long> map = listTwo.stream()
                .collect(Collectors.groupingBy(
                        num -> num,
                        Collectors.counting()
                ));
        Long similarityScore = 0L;
        for(int idInListOne : listOne) {
            Long occurrenceCount = idInListOne * map.getOrDefault(idInListOne, 0L);
            similarityScore += occurrenceCount;
        }
        System.out.println(similarityScore);
    }


    private void prepareData(List<String> rawInputData, List<Integer> listOne, List<Integer> listTwo) {
        rawInputData.forEach(item -> {
            String[] distanceIds = item.split(" {3}");
            listOne.add(Integer.parseInt(distanceIds[0]));
            listTwo.add(Integer.parseInt(distanceIds[1]));
        });
    }


}
