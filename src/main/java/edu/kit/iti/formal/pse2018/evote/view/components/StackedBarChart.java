/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package edu.kit.iti.formal.pse2018.evote.view.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * StackedBarChart is a JComponent which will draw a stacked bar chart on the given data.
 */
public class StackedBarChart extends Diagram {

    public static final int[] TEST_DATA = {4, 123, 64, 22, 5, 43, 83, 23, 12, 73, 22, 13, 2};
    public static final Color[] TEST_COLOR = {Color.BLACK, Color.RED, Color.YELLOW, Color.GREEN, Color.MAGENTA,
                                              Color.ORANGE, Color.PINK};

    @Override
    public void setData(int[] data) {
        assert (data.length >= 1);
        assert (data.length % data[0] == 1);

        super.setData(data);
    }

    @Override
    protected void drawChart(Graphics graphics) {
        if (data == null) {
            return;
        }
        if (colors == null) {
            colors = TEST_COLOR;
        }

        Graphics2D g = (Graphics2D) graphics;
        int barDist = 20;
        int barHeight = (getHeight() - data[0] * barDist) / (data[0] + 1);

        int segments = getColumns();

        int y = barHeight;
        int x = 0;
        for (int i = 0; i < getRows(); i++) {
            int sum = 0;
            for (int j = 0; j < segments; j++) {
                sum += accessData(i, j);
            }

            for (int j = 0; j < segments; j++) {
                int length = (int)((double) getWidth() * ((double) accessData(i, j) / (double) sum));
                g.setColor(getColor(j));
                g.fillRect(x, y, length, barHeight);
                x += length;
            }

            y += barDist + barHeight;
            x = 0;
        }
    }

    @Override
    protected void integrityChecks() {
        if (data != null) {
            int col = getColumns();
            int rows = getRows();
            assert (rows > 0);

            if (colors != null) {
                assert (colors.length >= col);
            }

            if (string != null) {
                assert (string.length >= col);
            }
        }
    }

    private int getColumns() {
        return data[0];
    }

    private int accessData(int row, int col) {
        int segments = getColumns();
        assert (1 + row * segments + col < data.length);
        return data[1 + row * segments + col];
    }

    private int getRows() {
        return (data.length - 1) / data[0];
    }

    private Color getColor(int i) {
        int cols = getColumns();
        return colors[i % cols];
    }
}
