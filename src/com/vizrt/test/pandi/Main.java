package com.vizrt.test.pandi;

import com.vizrt.test.pandi.io.swing.InputFrame;
import com.vizrt.test.pandi.process.IAlgorithmProcessor;
import com.vizrt.test.pandi.process.MyAlgorithmProcessor;
import com.vizrt.test.pandi.service.IntervalService;

public class Main {

    public static void main(String[] args)
    {
        IAlgorithmProcessor iAlgorithmProcessor = new MyAlgorithmProcessor(new IntervalService());
        InputFrame frame = new InputFrame("Programming Test Input", iAlgorithmProcessor);
        frame.setVisible(true);
    }
}
