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

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.ElectionRunningException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.exceptions.WrongCandidateNameException;
import edu.kit.iti.formal.pse2018.evote.model.VoterControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.VoterControlToViewIF;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class VoterAuthenticationListener extends VoterEventListener {

    public VoterAuthenticationListener(VoterControlToViewIF gui, VoterControlToModelIF model) {
        super(gui, model);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ResourceBundle lang = ResourceBundle.getBundle("VoterControl");
        String path = gui.getAuthenticationPath();
        try {
            model.authenticate(path);
            if (model.isElectionInitialized()) {
                if (model.hasVoted()) {
                    gui.showWait();
                } else {
                    gui.showChoice();
                }
            } else {
                gui.showError(lang.getString("noElectionRunning"));
            }
        } catch (InternalSDKException | NetworkException | AuthenticationException | NetworkConfigException e) {
            gui.showError(lang.getString("voterAuthenticationBad"));
            e.printStackTrace();
        } catch (WrongCandidateNameException e) {
            gui.showError(lang.getString("couldntLoadInitialData"));
            e.printStackTrace();
        } catch (ElectionRunningException e) {
            gui.showError(lang.getString("noElectionRunning"));
            e.printStackTrace();
            gui.exit();
        }
    }
}
