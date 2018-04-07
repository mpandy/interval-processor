package com.vizrt.test.pandi.io.swing;

import com.vizrt.test.pandi.process.IAlgorithmProcessor;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class InputFrame extends JFrame
{
    private JLabel label;
    private JTextArea inputArea;
    private JButton runButton;
    private IAlgorithmProcessor processor;

    public InputFrame(String title, IAlgorithmProcessor iAlgorithmProcessor) throws HeadlessException {

        processor = iAlgorithmProcessor;

        setTitle(title);
        setSize(new Dimension(500, 300));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        label = new JLabel("add includes in fir");
        inputArea = new JTextArea();
        inputArea.setBorder(new LineBorder(Color.BLUE));
        runButton = new JButton("Run");
        runButton.addActionListener(e -> processor.process(inputArea.getText()));

        Container c = getContentPane();
        c.add(label, BorderLayout.NORTH);
        c.add(inputArea, BorderLayout.CENTER);
        c.add(runButton, BorderLayout.SOUTH);
    }

}