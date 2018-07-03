package edu.kit.iti.formal.pse2018.evote.view.components;

import java.awt.Graphics;

/**
 * StackedBarChart is a JComponent which will draw a stacked bar chart on the given data.
 */
public class StackedBarChart extends Diagram{

    @Override
    public void setData(int[] data){
        //TODO verify data integrity
        super(data);
    }

    @Override
    protected void drawChart(Graphics graphics) {

    }
}
