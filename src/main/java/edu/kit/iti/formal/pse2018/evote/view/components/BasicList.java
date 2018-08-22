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

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

/**
 * BasicList is a very basic implementation of
 * NOTE: This Class can't be used alone. Use ExtendableList instead.
 */
public class BasicList extends JPanel {

    protected JPanel pnlScroll;
    protected JScrollPane spc;
    protected LinkedList<Entry> entries;

    /**
     * Creates a very basic List.
     */
    public BasicList() {
        this.setLayout(new BorderLayout());
        pnlScroll = new JPanel();

        GridLayout gl = new GridLayout(0, 1);
        pnlScroll.setLayout(gl);

        JPanel p = new JPanel(new BorderLayout());
        p.add(pnlScroll, BorderLayout.NORTH);

        spc = new JScrollPane(p);
        this.add(spc);

        entries = new LinkedList<>();
    }

    /**
     * Gives the number entries in the list.
     *
     * @return The number of Entries in this list.
     */
    public int getEntryCount() {
        return entries.size();
    }

    @Override
    public void setBorder(Border border) {
        if (spc != null) {
            spc.setBorder(border);
        }
    }
}
