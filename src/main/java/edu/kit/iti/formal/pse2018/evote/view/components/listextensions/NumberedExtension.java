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

import edu.kit.iti.formal.pse2018.evote.view.components.Entry;

import java.awt.Font;
import javax.swing.JLabel;

/**
 * NumberedExtension will give each Entry a JLabel which displays in which row that specific Entry is.
 */
public class NumberedExtension extends ComponentExtension<JLabel> {

    /**
     * Creates a new NumberedExtension Instance.
     * @param next the next Extension in the chain. Can be null.
     */
    public NumberedExtension(ListExtension next, Font font) {
        super(next, font);
    }

    @Override
    public void removeEntry(Entry e) {
        int i = searchIndex(e);
        components.remove(i);

        //Change numbers afterwards
        for (int j = i; j < components.size(); j++) {
            String t = genText(j);
            components.get(j).setText(t);
        }
    }

    @Override
    protected JLabel createNewType() {
        int nr = list.getEntries().size();
        JLabel lblNew = new JLabel(genText(nr));
        lblNew.setFont(font);

        return lblNew;
    }

    private String genText(int i) {
        return (i + 1) + ". ";
    }
}
