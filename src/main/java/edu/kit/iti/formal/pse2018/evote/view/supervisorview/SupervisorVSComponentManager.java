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

package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;

import java.awt.Color;

public abstract class SupervisorVSComponentManager {

    protected static final Color[] CANDIDATE_COLORS = {Color.BLACK, Color.GREEN,
        Color.BLUE, Color.RED, Color.YELLOW, Color.PINK, Color.ORANGE};

    protected SupervisorAdapter adapter;

    protected Diagram chart;
    protected ExtendableList table;

    public SupervisorVSComponentManager(SupervisorAdapter adapter) {
        this.adapter = adapter;
    }

    public abstract Diagram createResultDiagram();

    public abstract ExtendableList createResultTable();

    public abstract void updateComponents(int[] data);
}
