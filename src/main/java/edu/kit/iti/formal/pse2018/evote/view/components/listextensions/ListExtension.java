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
import edu.kit.iti.formal.pse2018.evote.view.components.Extendable;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;

/**
 * The ListExtension provides the basic functionality for a class to be used as an Extension in ExtendableLists.
 */
public abstract class ListExtension {

    protected ListExtension next;
    protected Extendable list;

    public ListExtension(ListExtension next) {
        this.next = next;
    }

    /**
     * An Entry is added to the list. The ListExtension can modify it.
     *
     * @param e the Entry that is added.
     */
    public void addEntry(Entry e) {
        if (next == null) {
            list.addEntry(e);
        } else {
            next.addEntry(e);
        }
    }

    /**
     * An Entry is removed to List. The ListExtension is notified so it can modify its state if needed.
     *
     * @param e The Entry that is to be removed.
     */
    public void removeEntry(Entry e) {
        if (next != null) {
            next.removeEntry(e);
        }
    }

    /**
     * Setting the List that is this ListExtension belongs to.
     *
     * @param list the list this Extension belongs to.
     */
    public final void setList(ExtendableList list) {
        if (list == null) {
            throw new IllegalArgumentException("List is Null");
        }
        this.list = list;
        if (next != null) {
            next.setList(list);
        }
    }

}
