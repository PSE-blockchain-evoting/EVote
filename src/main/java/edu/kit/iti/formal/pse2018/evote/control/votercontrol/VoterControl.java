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

package edu.kit.iti.formal.pse2018.evote.control.votercontrol;

import edu.kit.iti.formal.pse2018.evote.control.VoterViewToControlIF;
import edu.kit.iti.formal.pse2018.evote.model.VoterControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.VoterControlToViewIF;

import java.awt.event.ActionListener;

public class VoterControl implements VoterViewToControlIF {

    private VotedListener votedListener;
    private VoterAuthenticationListener authListener;
    private VoterLogoutListener logoutListener;

    /**
     * Create Controller for Voter.
     * @param gui VoterView
     * @param model VoterModel
     */
    public VoterControl(VoterControlToViewIF gui, VoterControlToModelIF model) {
        this.votedListener = new VotedListener(gui, model);
        this.authListener = new VoterAuthenticationListener(gui, model);
        this.logoutListener = new VoterLogoutListener(gui, model);
    }

    @Override
    public ActionListener getVotedListener() {
        return votedListener;
    }

    @Override
    public ActionListener getAuthenticationListener() {
        return authListener;
    }

    @Override
    public ActionListener getLogoutListener() {
        return logoutListener;
    }
}
