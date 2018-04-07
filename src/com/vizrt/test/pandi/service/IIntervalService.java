package com.vizrt.test.pandi.service;

import com.vizrt.test.pandi.model.Interval;

import java.util.List;
import java.util.Set;

public interface IIntervalService {
    List<Interval> buildFromString(String inputString);
    List<Interval> append(List<Interval> intervals);
    String print(List<Interval> intervals);
}
