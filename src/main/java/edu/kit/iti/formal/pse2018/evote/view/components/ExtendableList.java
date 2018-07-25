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

import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.ListExtension;

import java.util.List;

/**
 * ExtendableList extends BasicsList and adds plugin functionality based on ListExtensions.
 */
public class ExtendableList extends BasicList implements Extendable {

    protected ListExtension mod;

    public ExtendableList(ListExtension mod) {
        super();
        this.mod = mod;
    }

    @Override
    public void addEntry(Entry e) {
        entries.add(e);
        pnlScroll.add(e);
        refreshUI();
    }

    @Override
    public void addNewEntry() {
        Entry e = new Entry(entries.size());
        mod.addEntry(e);
    }

    @Override
    public void removeEntry(Entry e) {
        mod.removeEntry(e);
        entries.remove(e);
        pnlScroll.remove(e);
        refreshUI();
    }

    @Override
    public List<Entry> getEntries() {
        return entries;
    }

    private void refreshUI() {
        if (pnlScroll.getRootPane() != null) {
            pnlScroll.getRootPane().validate();
            pnlScroll.getRootPane().repaint();
        }
    }

    /**
     * Deletes every entry in the list.
     */
    public void clear() {
        while (entries.size() > 0) {
            removeEntry(entries.getLast());
        }
    }
}
