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
import java.util.LinkedList;
import javax.swing.JComponent;

/**
 * ComponentExtension extends the ListExtension for the functionality always adding a defined Component to an Entry.
 * @param <Type> The JComponent to add an Entry
 */
public abstract class ComponentExtension<Type extends JComponent> extends ListExtension {

    protected LinkedList<Type> components;
    protected Font font;

    /**
     * ComponentExtension is a specialized ListExtension which automatically adds a Type Component
     * to Entries of the list and manages them.
     * @param next The next ListExtension in the chain of ListExtensions. Can be null.
     */
    public ComponentExtension(ListExtension next, Font font) {
        super(next);

        this.font = font;
        components = new LinkedList<>();
    }

    @Override
    public void addEntry(Entry e) {
        Type tnew = createNewType();

        components.add(tnew);
        e.add(tnew);
        super.addEntry(e);
    }

    @Override
    public void removeEntry(Entry e) {
        super.removeEntry(e);
        int i = searchIndex(e);
        removeData(i);
    }

    protected void removeData(int i) {
        components.remove(i);
    }

    protected abstract Type createNewType();

    /**
     * Finds the index of an entry.
     * @param e The Entry so search,
     * @return The index of e.
     */
    protected int searchIndex(Entry e) {
        int n = 0;
        for (Entry e1 : list.getEntries()) {
            if (e1 == e) {
                return n;
            }
            n++;
        }

        assert (0 <= n && n < components.size());
        assert (components.get(n) == e);

        throw new IllegalStateException("This entry doesn't exist.");
    }
}
