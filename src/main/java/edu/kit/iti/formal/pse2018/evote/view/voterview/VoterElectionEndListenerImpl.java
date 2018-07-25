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

import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;

public class VoterElectionEndListenerImpl implements ElectionStatusListener {
    private VoterGUI gui;

    public VoterElectionEndListenerImpl(VoterGUI voterGUI) {
        this.gui = voterGUI;
    }

    @Override
    public void electionOver() {
        gui.electionOver();
    }

    @Override
    public void electionUpdate() {

    }
}
