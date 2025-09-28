package com.vi.dsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class MainDay5 {
    private final List<Rule> rules = new ArrayList<>();
    private final List<PrintQueue> printQueues = new ArrayList<>();

    private void instantiateData() throws IOException {
        URL refInput = MainDay5.class.getClassLoader().getResource("Day5RefInput.txt");
        URL tstInput = MainDay5.class.getClassLoader().getResource("Day5TestInput.txt");
        BufferedReader br = new BufferedReader(new FileReader(refInput.getFile()));

        while (br.ready()) {
            String line = br.readLine();
            String[] arr = line.split("\\|");
            Rule rule = new Rule(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
            rules.add(rule);
        }
        br.close();
        BufferedReader brTst = new BufferedReader(new FileReader(tstInput.getFile()));
        while (brTst.ready()) {
            String line = brTst.readLine();
            String[] arr = line.split("\\|");
            Rule rule = new Rule(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
            rules.add(rule);
        }
        brTst.close();

    }

    public String part1() {
        return Integer.toString(printQueues.stream().filter(this::isValid).mapToInt(PrintQueue::getMiddleValue).sum());
    }


    public String part2() {
        var invalids = printQueues.stream().filter(q->!isValid(q)).toList();
        invalids.forEach(this::fixInvalid);
        return Integer.toString(invalids.stream().mapToInt(PrintQueue::getMiddleValue).sum());
    }

    private boolean isValid(PrintQueue printQueue) {
        return rules.stream().allMatch(printQueue::matches);
    }

    private void fixInvalid(PrintQueue printQueue) {
        List<Rule> applicableRules = rules.stream().filter(printQueue::isApplicable).toList();
        int i = 0;
        while (i < applicableRules.size()) {
            Rule rule = applicableRules.get(i);
            if (!printQueue.matches(rule)) {
                printQueue.fixFor(rule);
                i = 0;
            } else {
                ++i;
            }
        }
    }

    private record Rule(int first, int second) {
        static Rule from(String rulDesc) {
            String[] parts = rulDesc.split("\\|");
            return new Rule(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }
    }

    private static class PrintQueue {
        private static final Comparator<Map.Entry<Integer, Integer>> ORDER_COMPARATOR = Comparator.comparingInt(Map.Entry::getValue);
        private final Map<Integer, Integer> pageToOrder;
        public PrintQueue(Map<Integer, Integer> pageToOrder) {
            this.pageToOrder = pageToOrder;
        }
        boolean matches(Rule rule) {
            if(pageToOrder.containsKey(rule.first) && pageToOrder.containsKey(rule.second)) {
                return pageToOrder.get(rule.first) < pageToOrder.get(rule.second);
            }
            return true;
        }
        void fixFor(Rule rule) {
            int firstOrder = pageToOrder.get(rule.first);
            pageToOrder.put(rule.first, pageToOrder.get(rule.second));
            pageToOrder.put(rule.second, firstOrder);
        }
        int getMiddleValue() {
            return pageToOrder.entrySet().stream().sorted(ORDER_COMPARATOR).skip(pageToOrder.size() / 2).limit(1).findFirst().map(Map.Entry::getKey).orElseThrow();
        }
        boolean isApplicable(Rule rule) {
            return pageToOrder.containsKey(rule.first()) && pageToOrder.containsKey(rule.second());
        }
        static PrintQueue from(String queueDesc) {
            Map<Integer, Integer> pageToOrder = new HashMap<>();
            int order = 0;
            for(String s: queueDesc.split(",")) {
                pageToOrder.put(Integer.parseInt(s), order++);
            }
            return new PrintQueue(pageToOrder);
        }
    }
}
