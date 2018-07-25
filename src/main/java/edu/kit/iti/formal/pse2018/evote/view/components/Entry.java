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

import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * This class represents a row in a BasicList.
 */
public class Entry extends JPanel {

    public Entry(int i) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    @Override
    public String toString() {
        return Arrays.toString(super.getComponents());
    }
}
