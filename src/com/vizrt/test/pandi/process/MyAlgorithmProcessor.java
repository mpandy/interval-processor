package com.vizrt.test.pandi.process;

import com.vizrt.test.pandi.model.Interval;
import com.vizrt.test.pandi.service.IIntervalService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyAlgorithmProcessor implements IAlgorithmProcessor {

    private IIntervalService iIntervalService;

    public MyAlgorithmProcessor(IIntervalService iIntervalService) {
        this.iIntervalService = iIntervalService;
    }

    @Override
    public void process(String input) {
        String lines[] = input.split("\\r?\\n");
        if(lines.length > 2)
            System.err.println("too much parameter");

        String includeLine = purifyInputString(lines[0]);
        String excludeLine = purifyInputString(lines[1]);

        List<Interval> includes = iIntervalService.buildFromString(includeLine);
        List<Interval> excludes = iIntervalService.buildFromString(excludeLine);

        Collections.sort(includes);
        Collections.sort(excludes);

        List<Interval> appendedIncludes = iIntervalService.append(includes);
        List<Interval> appendedExcludes = iIntervalService.append(excludes);

        if(appendedExcludes.size()==0)
            System.out.println(iIntervalService.print(appendedIncludes));

        List<Interval> finalResult = new ArrayList<>();
        int includeCounter = 0;
        for(Interval nextExclude : appendedExcludes){
            for(;includeCounter < appendedIncludes.size() ; includeCounter++)
            {
                Interval intersection = appendedIncludes.get(includeCounter).intersection(nextExclude);
                if(intersection.length() == 0)
                    break;
                else
                    finalResult.addAll(appendedIncludes.get(includeCounter).minus(nextExclude));
            }
        }

        System.out.println(iIntervalService.print(finalResult));
        System.out.println("************************************************");
        System.out.println("***** My algorithm complexity is O(NlogN)*******");
        System.out.println("************************************************");
    }

    private String purifyInputString(String input){
        return input.replace("Includes:", "")
                    .replace("Excludes:", "")
                    .replace("(none)", "")
                    .trim();
    }
}
