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

package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class ConfirmedConfigListener extends SupervisorEventListener {

    public ConfirmedConfigListener(SupervisorControlToViewIF gui, SupervisorControlToModelIF model) {
        super(gui, model);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorControl");
        ElectionDataIF data = gui.getElectionData();
        model.setVoters(gui.getVoters());
        boolean b = model.setElectionData(data);
        if (b) {
            gui.showSuccess(lang.getString("confirmedConfigSuccess"));
        } else {
            gui.showConfigIssues();
            gui.showError(lang.getString("confirmedConfigBad"));
        }
    }
}
