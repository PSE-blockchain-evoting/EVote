package edu.kit.iti.formal.pse2018.evote.view.components;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * This class provides the abstract framework for implementing a wide range of different Diagrams.
 */
public abstract class Diagram extends JComponent {
    protected int[] data;
    protected Color[] colors;
    protected String[] string;

    public Diagram(){

    }

    public int[] getData() {
        return data;
    }

    /**
     * Sets a new data sets and redraws diagram.
     * @param data The new data.
     */
    public void setData(int[] data) {
        this.data = data;
        integrityChecks();
        this.repaint();
    }

    public Color[] getColors() {
        return colors;
    }

    /**
     * Sets a new Colors and redraws diagram.
     * @param colors The new colors.
     */
    public void setColors(Color[] colors) {
        this.colors = colors;
        integrityChecks();
        this.repaint();
    }

    public String[] getString() {
        return string;
    }

    /**
     * Sets a new Strings and redraws diagram.
     * @param string The new strings.
     */
    public void setString(String[] string) {
        this.string = string;
        integrityChecks();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        drawChart(graphics);
    }

    protected abstract void drawChart(Graphics graphics);

    protected abstract void integrityChecks();
}
