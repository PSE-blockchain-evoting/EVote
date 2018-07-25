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

package edu.kit.iti.formal.pse2018.evote.view.supervisorview.configpanels;

import edu.kit.iti.formal.pse2018.evote.view.components.CandidateList;
import edu.kit.iti.formal.pse2018.evote.view.components.VerticalTabs;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.ConfigGUI;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.SupervisorAdapter;

import java.awt.LayoutManager2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

/**
 * CandidatePanel lets the user configure the Candidates with their respective Description.
 */
public class CandidatePanel extends ConfigPanel {

    private CandidateList candidates;
    private JButton btnAdd;

    public CandidatePanel(JPanel container, ConfigGUI gui, VerticalTabs vt, SupervisorAdapter adapter) {
        super(container, gui, vt, adapter);
    }

    @Override
    protected void initComponents() {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorConfig");

        JFrame parent = (JFrame) getTopLevelAncestor();
        candidates = CandidateList.createCandidateList(parent);
        candidates.addNewEntry();

        btnAdd = new JButton(lang.getString("btnCandidateAddText"));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                candidates.addNewEntry();
            }
        });
    }

    @SuppressWarnings({"checkstyle:linelength", "checkstyle:Indentation"})
    @Override
    protected LayoutManager2 buildLayout(JPanel container) {
        GroupLayout gl = new GroupLayout(container);

        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
                        .addGroup(gl.createParallelGroup()
                                .addComponent(candidates, 0, 1, Short.MAX_VALUE)
                                .addComponent(btnAdd)
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
        );

        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
                        .addComponent(candidates, 0, 1, Short.MAX_VALUE)
                        .addComponent(btnAdd)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
        );
        return gl;
    }

    public String[] getCandidateNames() {
        return candidates.getCandidateNames();
    }

    public void setCandidateNames(String[] names) {
        candidates.setCandidateNames(names);
    }

    public String[] getCandidateDescriptions() {
        return candidates.getCandidateDescriptions();
    }

    public void setCandidateDescriptions(String[] descs) {
        candidates.setCandidateDescriptions(descs);
    }
}
