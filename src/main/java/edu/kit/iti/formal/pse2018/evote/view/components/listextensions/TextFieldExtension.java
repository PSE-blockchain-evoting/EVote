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
import javax.swing.JTextField;

/**
 * TextFieldExtension will add a JTextField to an Entry.
 */
public class TextFieldExtension extends ComponentExtension<JTextField> {

    public TextFieldExtension(ListExtension next, Font font) {
        super(next, font);
    }

    @Override
    protected JTextField createNewType() {
        JTextField txf = new JTextField();
        txf.setFont(font);
        return txf;
    }

    /**
     * get the text of an Entry.
     *
     * @param i the index of the Entry.
     * @return The Text in the specified Entry.
     */
    public String getText(int i) {
        assert (0 <= i && i < components.size());
        return components.get(i).getText();
    }

    /**
     * Set the text of an Entry.
     *
     * @param i    the index of the Entry.
     * @param text The text to set.
     */
    public void setText(int i, String text) {
        while (i >= components.size()) {
            list.addNewEntry();
        }
        components.get(i).setText(text);
    }

    /**
     * Allow editing of the Textfields.
     *
     * @param b enable/disable.
     */
    public void setEditable(boolean b) {
        for (JTextField txf : components) {
            txf.setEditable(b);
        }
    }
}
