package com.vizrt.test.pandi.service;

import com.vizrt.test.pandi.model.Interval;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IntervalService implements IIntervalService{

    @Override
    public List<Interval> buildFromString(String s) {
        List<Interval> intervals = new ArrayList<>();
        for(String nextInterval : s.split(",")) {
            String[] coordinates = nextInterval.trim().split("-");
            if(!coordinates[0].isEmpty() && !coordinates[1].isEmpty())
                intervals.add(new Interval(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
        }
        return intervals;
    }

    @Override
    public List<Interval> append(List<Interval> intervals) {

        if(intervals.size() == 1)
            return intervals;

        int i = 0;
        List<Interval> appendResult = new ArrayList();
        while(i+1 < intervals.size()) {
            for (Interval interval : intervals.get(i).append(intervals.get(i+1)))
                if(!appendResult.contains(interval))
                    appendResult.add(interval);
            i++;
        }
        return appendResult;
    }

    @Override
    public String print(List<Interval> intervals) {
        String raw = intervals.toString();
        return raw.substring(1, raw.length()-1);
    }


}
