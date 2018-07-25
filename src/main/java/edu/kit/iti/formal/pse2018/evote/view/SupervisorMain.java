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

import edu.kit.iti.formal.pse2018.evote.view.supervisorview.SupervisorGUI;

import java.awt.Font;
import java.util.Locale;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class SupervisorMain {

    /**
     * Program Entry point. Starts GUI for Supervisor.
     * @param args irrelevant
     */
    public static void main(String[] args) {
        Locale.setDefault(new Locale("de", "DE"));
        UIManager.put("Title.font", new FontUIResource("Sans Serif", Font.BOLD, 35));
        UIManager.put("General.font", new FontUIResource("Sans Serif", Font.BOLD, 15));
        UIManager.put("Vote.font", new FontUIResource("Sans Serif", Font.BOLD, 30));

        SupervisorGUI gui = new SupervisorGUI();
    }
}
