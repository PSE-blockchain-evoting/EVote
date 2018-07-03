package edu.kit.iti.formal.pse2018.evote.view.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;

/**
 * PieChart is a JComponent which draws a PieChart based on the given data.
 */
public class PieChart extends Diagram {

    public static final int[] TEST_DATA = {100, 75, 32, 10, 8, 3, 2};
    public static final Color[] TEST_COLOR = {Color.BLACK, Color.RED, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK};

    @Override
    protected void drawChart(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;

        int width = getWidth();
        int height = getHeight();

        int size = width < height ? width : height;
        int x0 = width < height ? 0 : (width - size) / 2;
        int y0 = width < height ? (height - size) / 2 : 0;

        g.setColor(Color.CYAN);

        int sum = 0;
        for (int i = 0; i < data.length; i++) { sum += data[i]; }

        double ratio = 360.0 / sum;

        double cur = 0;
        for (int i = 0; i < data.length; i++) {
            g.setColor(colors[i]);
            g.fillArc(x0, y0, size, size, (int)Math.round(ratio * cur), (int)Math.round(data[i] * ratio));
            cur += data[i];
        }

        //cur = 0;
        //g.setColor(Color.BLACK);
        //g.setStroke(new BasicStroke(4));
        //for (int i = 0; i < data.length; i++) {
        //    g.drawArc(x0, y0, size, size, (int)Math.round(ratio * cur), (int)Math.round(data[i] * ratio));
        //    cur += data[i];
        //}
    }
}
