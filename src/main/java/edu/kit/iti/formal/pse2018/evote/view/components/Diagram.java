package edu.kit.iti.formal.pse2018.evote.view.components;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * This class provides the abstract framework for implementing a wide range of different Diagrams.
 */
public abstract class Diagram extends JComponent {
    protected int[] data;
    protected String[] colors;
    protected Color[] string;

    public Diagram(){

    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public Color[] getString() {
        return string;
    }

    public void setString(Color[] string) {
        this.string = string;
    }

    @Override
    protected void printComponent(Graphics graphics) {
        drawChart(graphics);
    }

    protected abstract void drawChart(Graphics graphics):
}
