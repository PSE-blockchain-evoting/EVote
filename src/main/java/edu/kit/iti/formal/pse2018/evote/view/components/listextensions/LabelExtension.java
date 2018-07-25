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

import java.awt.Font;
import javax.swing.JLabel;

/**
 * LabelExtension will add a JLabel on each Entry. Every JLabel will show the same text.
 */
public class LabelExtension extends ComponentExtension<JLabel> {

    private static final String TEXT = "Hello World";
    private String text;

    public LabelExtension(ListExtension next, Font font, String text) {
        super(next, font);
        this.text = text;
    }

    @Override
    protected JLabel createNewType() {
        JLabel lbl = new JLabel(TEXT);
        lbl.setFont(font);
        return lbl;
    }
}
