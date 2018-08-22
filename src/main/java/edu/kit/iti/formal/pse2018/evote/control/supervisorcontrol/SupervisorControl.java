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

import edu.kit.iti.formal.pse2018.evote.control.SupervisorViewToControlIF;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;

import java.awt.event.ActionListener;

public class SupervisorControl implements SupervisorViewToControlIF {

    private ConfirmedConfigListener confirmedConfigListener;
    private ExportConfigListener expConfigListener;
    private ImportConfigListener impConfigListener;
    private FinishElectionListener finishElectionListener;
    private NewConfigListener newConfigListener;
    private StartElectionListener startElectionListener;
    private SupervisorLogoutListener logoutListener;
    private FirstAuthenticationListener firstAuthenticationListener;
    private SupervisorAuthenticationListener authenticationListener;

    /**
     * Create Controller for Supervisor.
     * @param gui SupervisorView
     * @param model SupervisorModel
     */
    public SupervisorControl(SupervisorControlToViewIF gui, SupervisorControlToModelIF model) {
        this.confirmedConfigListener = new ConfirmedConfigListener(gui, model);
        this.expConfigListener = new ExportConfigListener(gui, model);
        this.impConfigListener = new ImportConfigListener(gui, model);
        this.finishElectionListener = new FinishElectionListener(gui, model);
        this.newConfigListener = new NewConfigListener(gui, model);
        this.startElectionListener = new StartElectionListener(gui, model);
        this.logoutListener = new SupervisorLogoutListener(gui, model);
        this.firstAuthenticationListener = new FirstAuthenticationListener(gui, model);
        this.authenticationListener = new SupervisorAuthenticationListener(gui, model);
    }

    @Override
    public ActionListener getFinishElectionListener() {
        return finishElectionListener;
    }

    @Override
    public ActionListener getImportConfigListener() {
        return impConfigListener;
    }

    @Override
    public ActionListener getExportConfigListener() {
        return expConfigListener;
    }

    @Override
    public ActionListener getFirstAuthenticationListener() {
        return firstAuthenticationListener;
    }

    @Override
    public ActionListener getConfirmedConfigListener() {
        return confirmedConfigListener;
    }

    @Override
    public ActionListener getNewConfigListener() {
        return newConfigListener;
    }

    @Override
    public ActionListener getStartElectionListener() {
        return startElectionListener;
    }

    @Override
    public ActionListener getAuthenticationListener() {
        return authenticationListener;
    }

    @Override
    public ActionListener getLogoutListener() {
        return logoutListener;
    }
}
