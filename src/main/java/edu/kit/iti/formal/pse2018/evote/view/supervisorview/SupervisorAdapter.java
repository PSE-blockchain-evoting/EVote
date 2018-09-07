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

import edu.kit.iti.formal.pse2018.evote.control.SupervisorViewToControlIF;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorViewToModelIF;
import edu.kit.iti.formal.pse2018.evote.utils.ConfigIssues;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.awt.event.ActionListener;

public class SupervisorAdapter {
    private SupervisorViewToControlIF control;
    private SupervisorViewToModelIF model;

    public SupervisorAdapter(SupervisorViewToControlIF control, SupervisorViewToModelIF model) {
        this.control = control;
        this.model = model;
    }

    public ActionListener getFinishElectionListener() {
        return control.getFinishElectionListener();
    }

    public ActionListener getImportConfigListener() {
        return control.getImportConfigListener();
    }

    public ActionListener getExportConfigListener() {
        return control.getExportConfigListener();
    }

    public ActionListener getFirstAuthenticationListener() {
        return control.getFirstAuthenticationListener();
    }

    public ActionListener getConfirmedConfigListener() {
        return control.getConfirmedConfigListener();
    }

    public ActionListener getNewConfigListener() {
        return control.getNewConfigListener();
    }

    public ActionListener getStartElectionListener() {
        return control.getStartElectionListener();
    }

    public ActionListener getAuthenticationListener() {
        return control.getAuthenticationListener();
    }

    public ActionListener getLogoutListener() {
        return control.getLogoutListener();
    }

    public String[] getVoters() {
        return model.getVoters();
    }

    public ConfigIssues getConfigIssues() {
        return model.getConfigIssues();
    }

    public ElectionDataIF getElectionData() {
        return model.getElectionData();
    }

    public String getWinner() {
        return model.getWinner();
    }

    public void setElectionEndListener(ElectionStatusListener listener) {
        model.setElectionStatusListener(listener);
    }

    public int[] getResults() {
        return model.getResults();
    }

    public boolean isElectionOver() {
        return model.isElectionOver();
    }
}
