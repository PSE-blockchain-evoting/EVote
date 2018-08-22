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

import java.lang.invoke.WrongMethodTypeException;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class SupervisorGUIPanel extends JPanel {

    protected SupervisorAdapter adapter;
    private JLabel lblTitle;
    private JPanel pnlLogo;

    /**
     * Creates an instance of SupervisorGUIPanel.
     *
     * @param adapter The Adapter to the control and model interfaces.
     */
    public SupervisorGUIPanel(SupervisorAdapter adapter) {
        this.adapter = adapter;
    }

    public void updateResults(int[] results, String winner) {
        throw new WrongMethodTypeException();
    }

    public String getImportPath() {
        throw new WrongMethodTypeException();
    }

    public String getCertPath() {
        throw new WrongMethodTypeException();
    }

    public String getUsername() {
        throw new WrongMethodTypeException();
    }

    public String getPassword() {
        throw new WrongMethodTypeException();
    }
}
