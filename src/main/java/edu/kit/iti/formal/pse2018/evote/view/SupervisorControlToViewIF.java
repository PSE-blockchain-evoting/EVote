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

package edu.kit.iti.formal.pse2018.evote.view;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.awt.event.ActionListener;

public interface SupervisorControlToViewIF extends ControlToViewIF {

    public void showFrontpage();

    public void startConfigMenu();

    public void loadConfigData();

    public String[] getVoters();

    public ElectionDataIF getElectionData();

    public String getImportPath();

    public String getExportPath();

    public void showConfigIssues();

    public String getPassword();

    public String getUsername();

    public void updateResult() throws NetworkConfigException, NetworkException;
}
