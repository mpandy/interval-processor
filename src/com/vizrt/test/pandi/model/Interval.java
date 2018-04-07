package com.vizrt.test.pandi.model;

import java.util.Arrays;
import java.util.List;

public class Interval implements Comparable{

    private int start;
    private int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int length() {
            return this.getEnd() - this.getStart();
    }

    public Interval intersection(Interval interval){

//        makeSureThatInputIntervalIsShorter(interval);
        if(this.includes(interval))
            return interval;

        if(interval.getStart() < this.getEnd() && interval.getEnd() > this.getEnd())
            return new Interval(interval.getStart(), this.getEnd());

        if(interval.getStart() < this.getStart() && interval.getEnd() > this.getStart())
            return new Interval(this.getStart(), interval.getEnd());

        return new Interval(0, 0);
    }

    public List<Interval> minus(Interval interval) {
        if(this.includes(interval))
            return Arrays.asList(new Interval(this.getStart(), interval.getStart()-1),
                                 new Interval(interval.getEnd()+1, this.getEnd()));
        Interval intersection  = this.intersection(interval);
        if(intersection.startsWith(this.getStart()))
            return Arrays.asList(new Interval(intersection.getEnd()+1, this.getEnd()));

        if(intersection.endsWith(this.getEnd()))
            return Arrays.asList(new Interval(this.getStart(), intersection.getStart()-1));

        return Arrays.asList(this);
    }

    private boolean includes(Interval interval) {
        return  this.getStart() <= interval.getStart() &&
                this.getEnd() >= interval.getEnd();
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public List<Interval> append(Interval interval){
        if(this.intersection(interval).length() > 0){
            if(this.includes(interval))
                return Arrays.asList(this);
            else
                return Arrays.asList(new Interval(this.getStart(), interval.getEnd()));
        }
        else{
            return Arrays.asList(this, interval);
        }
    }

    @Override
    public int compareTo(Object o) {
        return  this.getStart() - ((Interval) o).getStart();
    }

    private boolean endsWith(int end){
        return this.getEnd()==end;
    }

    private boolean startsWith(int start){
        return this.getStart()==start;
    }

    @Override
    public String toString() {
        return start + "-" + end;
    }

    @Override
    public boolean equals(Object obj) {
        return  this.start == ((Interval)(obj)).getStart() &&
                this.end == ((Interval)(obj)).getEnd();
    }
}
