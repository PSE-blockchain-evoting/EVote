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

package edu.kit.iti.formal.pse2018.evote.view.components.listextensions;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GapExtension extends ComponentExtension<JPanel> {

    /**
     * ComponentExtension is a specialized ListExtension which automatically adds a Type Component
     * to Entries of the list and manages them.
     *
     * @param next The next ListExtension in the chain of ListExtensions. Can be null.
     * @param font The font used.
     */
    public GapExtension(ListExtension next, Font font) {
        super(next, font);
    }

    @Override
    protected JPanel createNewType() {
        JPanel pnl = new TransparentPanel();
        pnl.setSize(new Dimension(10000, 10));
        return pnl;
    }

    private class TransparentPanel extends JPanel {
        @Override
        public void paintComponent(Graphics graphics) {

        }
    }
}
