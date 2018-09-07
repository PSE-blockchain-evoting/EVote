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

package edu.kit.iti.formal.pse2018.evote.view.voterview;

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;

import java.awt.Color;
import javax.swing.JLabel;

public abstract class VoterVSComponentManager {

    protected static final Color[] CANDIDATE_COLORS = {Color.BLACK, Color.GREEN,
        Color.BLUE, Color.RED, Color.YELLOW, Color.PINK, Color.ORANGE};

    protected VoterAdapter adapter;

    protected Diagram chart;
    protected ExtendableList table;
    protected JLabel lblTableDescription;

    public VoterVSComponentManager(VoterAdapter adapter) {
        this.adapter = adapter;
    }

    public abstract Diagram createResultDiagram();

    public abstract ExtendableList createVotingForm();

    public abstract JLabel createTableDescriptionLabel();

    public abstract String getVote();

    public abstract void setVote(String vote);

    public abstract void setEditable(boolean b);

    public abstract void enableColors(boolean b);
}
