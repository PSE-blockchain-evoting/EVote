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

package edu.kit.iti.formal.pse2018.evote.view.voterview;

import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.InformationPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;

public class VoterChoice extends VoterGUIPanel {

    VoterVSComponentManager manager;
    ExtendableList form;

    JButton btnConfirmVote;
    JButton btnExit;
    JToggleButton btnInfo;
    InformationPanel pnlInfos;
    JScrollPane spInfos;

    GroupLayout layout;

    /**
     * Creates an instance of VoterChoice.
     *
     * @param adapter The context of the GUI.
     * @param manager The manager for creating VotingSystem dependend components.
     */
    public VoterChoice(VoterAdapter adapter, VoterVSComponentManager manager) {
        super(adapter);
        this.manager = manager;

        form = manager.createVotingForm();

        initComponents();
        buildLayout();
    }

    private void initComponents() {
        ResourceBundle lang = ResourceBundle.getBundle("VoterView");
        Font f = (Font) UIManager.get("General.font");
        btnInfo = new JToggleButton(lang.getString("btnInfoText"));
        btnInfo.setFont(f);
        btnInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                spInfos.setVisible(btnInfo.isSelected());
            }
        });
        btnConfirmVote = new JButton(lang.getString("btnConfirmVoteText"));
        btnConfirmVote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String s = lang.getString("ConfirmVoteWarning");
                int res = JOptionPane.showConfirmDialog(VoterChoice.this,
                        lang.getString("ConfirmVoteWarning"),
                        lang.getString("ConfirmVoteTitle"),
                        JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION) {
                    adapter.getVotedListener().actionPerformed(actionEvent);
                }
            }
        });
        btnInfo.setFont(f);
        pnlInfos = new InformationPanel(adapter.getElectionData());
        spInfos = new JScrollPane(pnlInfos);
        spInfos.setVisible(false);

        btnExit = new JButton(lang.getString("btnExitText"));
        btnExit.setFont(f);
        btnExit.addActionListener(adapter.getLogoutListener());
    }

    private void buildLayout() {
        layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(form)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnConfirmVote)
                                .addGap(0, 0, Short.MAX_VALUE)
                        )
                )
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnInfo)
                                .addGap(0, 0, Short.MAX_VALUE)
                        )
                        .addComponent(spInfos, 0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnExit)
                        )
                )
        );

        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                        .addComponent(form, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                        .addComponent(btnConfirmVote)
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInfo)
                        .addComponent(spInfos)
                        .addComponent(btnExit)
                )
        );
        layout.setHonorsVisibility(false);
        this.setLayout(layout);
    }

    @Override
    public String getVote() {
        return manager.getVote();
    }
}
