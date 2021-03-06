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
 * PieChart is a JComponent which draws a PieChart based on the given data.
 */
public class PieChart extends Diagram {

    public static final int[] TEST_DATA = {100, 75, 32, 10, 8, 3, 2};
    public static final Color[] TEST_COLOR = {Color.BLACK, Color.RED, Color.YELLOW,
        Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK};

    @Override
    protected void drawChart(Graphics graphics) {
        if (data == null) {
            return;
        }
        if (colors == null) {
            colors = TEST_COLOR;
        }

        Graphics2D g = (Graphics2D) graphics;

        int width = getWidth();
        int height = getHeight();

        int size = width < height ? width : height;
        int x0 = width < height ? 0 : (width - size) / 2;
        int y0 = width < height ? (height - size) / 2 : 0;

        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }

        double ratio = 360.0 / sum;

        double cur = 0;
        for (int i = 0; i < data.length; i++) {
            g.setColor(colors[i % colors.length]);
            g.fillArc(x0, y0, size, size, (int) Math.round(ratio * cur), (int) Math.round(data[i] * ratio));
            cur += data[i];
        }
    }

    protected void integrityChecks() {
        if (colors != null && data != null) {
            assert (colors.length >= data.length);
        }

        if (string != null && data != null) {
            assert (string.length >= data.length);
        }
    }
}
