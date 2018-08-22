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

import edu.kit.iti.formal.pse2018.evote.control.VoterViewToControlIF;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.VoterViewToModelIF;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.awt.event.ActionListener;

public class VoterAdapter {

    private VoterViewToModelIF model;
    private VoterViewToControlIF control;

    public VoterAdapter(VoterViewToControlIF control, VoterViewToModelIF model) {
        this.model = model;
        this.control = control;
    }

    public ActionListener getVotedListener() {
        return control.getVotedListener();
    }

    public ActionListener getAuthenticationListener() {
        return control.getAuthenticationListener();
    }

    public ActionListener getLogoutListener() {
        return control.getLogoutListener();
    }

    public String getOwnVote() {
        return model.getOwnVote();
    }

    public ElectionDataIF getElectionData() {
        return model.getElectionData();
    }

    public String getWinner() {
        return model.getWinner();
    }

    public void setElectionStatusListener(ElectionStatusListener listener) {
        model.setElectionStatusListener(listener);
    }

    public int[] getResults() {
        return model.getResults();
    }
}
