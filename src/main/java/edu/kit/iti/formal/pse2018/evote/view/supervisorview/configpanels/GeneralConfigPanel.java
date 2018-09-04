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

import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;
import edu.kit.iti.formal.pse2018.evote.view.components.VerticalTabs;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.ConfigGUI;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.SupervisorAdapter;

import java.awt.Font;
import java.awt.LayoutManager2;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * GeneralConfigPanel lets the user enter general information about the election.
 * This includes Name, Description and Voting System.
 */
public class GeneralConfigPanel extends ConfigPanel {

    protected JLabel lblName;
    protected JLabel lblVotingSystem;
    protected JLabel lblDescription;
    protected JScrollPane spDescription;
    protected JTextField txfName;
    protected JComboBox<String> cbxVotingSystem;
    protected JTextArea txaDescription;


    public GeneralConfigPanel(JPanel container, ConfigGUI gui, VerticalTabs vt, SupervisorAdapter adapter) {
        super(container, gui, vt, adapter);
    }

    protected void initComponents() {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorConfig");

        lblName = new JLabel(lang.getString("lblNameText") + ":");
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblVotingSystem = new JLabel(lang.getString("lblVotingSystemText") + ":");
        lblVotingSystem.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescription = new JLabel(lang.getString("lblDescriptionText") + ":");
        lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
        txfName = new JTextField();
        cbxVotingSystem = new JComboBox<>();
        ResourceBundle viewLang = ResourceBundle.getBundle("View");
        cbxVotingSystem.addItem(viewLang.getString(VotingSystemType.ABSOLUTEMAJORITY.name()));
        cbxVotingSystem.addItem(viewLang.getString(VotingSystemType.RELATIVEMAJORITY.name()));
        cbxVotingSystem.addItem(viewLang.getString(VotingSystemType.INSTANTRUNOFF.name()));
        txaDescription = new JTextArea();
        txaDescription.setLineWrap(true);
        txaDescription.setWrapStyleWord(true);
        spDescription = new JScrollPane(txaDescription);

        lblName.setFont((Font) UIManager.get("General.font"));
        lblVotingSystem.setFont((Font) UIManager.get("General.font"));
        lblDescription.setFont((Font) UIManager.get("General.font"));
        txfName.setFont((Font) UIManager.get("General.font"));
        cbxVotingSystem.setFont((Font) UIManager.get("General.font"));
        txaDescription.setFont((Font) UIManager.get("General.font"));
    }

    @SuppressWarnings({"checkstyle:linelength", "checkstyle:Indentation"})
    @Override
    protected LayoutManager2 buildLayout(JPanel container) {
        GroupLayout generalLayout = new GroupLayout(container);

        generalLayout.setAutoCreateContainerGaps(true);
        generalLayout.setAutoCreateGaps(true);

        generalLayout.setHorizontalGroup(
                generalLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
                        .addGroup(generalLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGroup(generalLayout.createSequentialGroup()
                                        .addComponent(lblName, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txfName, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                )
                                .addGroup(generalLayout.createSequentialGroup()
                                        .addComponent(lblVotingSystem, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbxVotingSystem, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                )
                                .addGroup(generalLayout.createSequentialGroup()
                                        .addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spDescription, 0, 0, Short.MAX_VALUE)
                                )
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 200)
        );

        generalLayout.setVerticalGroup(
                generalLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
                        .addGroup(generalLayout.createSequentialGroup()
                                .addGroup(generalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblName, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txfName, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(generalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblVotingSystem, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbxVotingSystem, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(generalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spDescription, 0, 0, Short.MAX_VALUE)
                                )
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, 100)
        );

        generalLayout.linkSize(SwingConstants.HORIZONTAL, lblName, lblDescription, lblVotingSystem);
        return generalLayout;
    }

    public String getElectionName() {
        return txfName.getText();
    }

    public void setElectionName(String name) {
        txfName.setText(name);
    }

    public String getElectionDescription() {
        return txaDescription.getText();
    }

    public void setElectionDescription(String desc) {
        txaDescription.setText(desc);
    }

    /**
     * Extracts the entered VotingSystem.
     *
     * @return The VotingSystem Enum if succesfull, otherwise null.
     */
    public VotingSystemType getVotingSystem() {
        VotingSystemType t;
        switch (cbxVotingSystem.getSelectedIndex()) {
            case 0:
                t = VotingSystemType.ABSOLUTEMAJORITY;
                break;
            case 1:
                t = VotingSystemType.RELATIVEMAJORITY;
                break;
            case 2:
                t = VotingSystemType.INSTANTRUNOFF;
                break;
            default:
                throw new IllegalStateException("Unknown VotingSystem selected.");
        }
        return t;
    }

    public void setVotingSystem(VotingSystemType sys) {
        ResourceBundle lang = ResourceBundle.getBundle("View");
        cbxVotingSystem.setSelectedItem(lang.getString(sys.name()));
    }

    @Override
    public void onDeactivate() {
        gui.setTitle(txfName.getText());
    }
}
