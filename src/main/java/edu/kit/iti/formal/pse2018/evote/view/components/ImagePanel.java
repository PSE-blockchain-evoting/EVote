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

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel() {
        super();
    }

    public ImagePanel(BufferedImage image) {
        this();
        this.image = image;
    }

    public void setImage(BufferedImage newImage) {
        this.image = newImage;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        int width = getWidth();
        int height = getHeight();

        float imgRatio = (float) image.getWidth() / image.getHeight();
        float pnlRatio = (float) width / height;

        int drawWidth;
        int drawHeight;

        if (imgRatio > pnlRatio) {
            //Reduce image width
            drawWidth = width;
            drawHeight = (int)(1 / imgRatio * width);
        } else {
            //Reduce image height
            drawWidth = (int)(imgRatio * height);
            drawHeight = height;
        }
        graphics.drawImage(image, 0, 0, drawWidth, drawHeight, null);
    }
}
