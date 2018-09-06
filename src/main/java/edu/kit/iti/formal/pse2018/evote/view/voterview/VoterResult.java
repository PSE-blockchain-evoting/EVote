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

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.InformationPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;

public class VoterResult extends VoterGUIPanel {

    private VoterVSComponentManager manager;

    private Diagram chart;
    private ExtendableList form;

    private JButton btnExit;
    private JToggleButton btnInfos;
    private InformationPanel pnlInfos;
    private JScrollPane spInfos;
    private JLabel lblWinner;
    private JLabel lblVoterName;

    private GroupLayout layout;

    /**
     * Creates an instance of VoterResult.
     *
     * @param adapter The context of the UI.
     * @param manager The componentManager for the VotingSystem.
     */
    public VoterResult(VoterAdapter adapter, VoterVSComponentManager manager) {
        super(adapter);
        this.manager = manager;

        chart = manager.createResultDiagram();
        String vote = adapter.getOwnVote();
        if (vote != null) {
            manager.setVote(adapter.getOwnVote());
        }
        manager.setEditable(false);
        form = manager.createVotingForm();

        initComponents();
        buildLayout();
    }

    private void initComponents() {
        ResourceBundle lang = ResourceBundle.getBundle("VoterView");
        Font f = (Font) UIManager.get("general.font");
        btnExit = new JButton(lang.getString("btnExitText"));
        btnExit.setFont(f);
        btnExit.addActionListener(adapter.getLogoutListener());

        pnlInfos = new InformationPanel(adapter.getElectionData());
        spInfos = new JScrollPane(pnlInfos);

        btnInfos = new JToggleButton(lang.getString("btnInfoText"));
        btnInfos.setFont(f);
        btnInfos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (btnInfos.isSelected()) {
                    layout.replace(chart, spInfos);
                } else {
                    layout.replace(spInfos, chart);
                }
            }
        });

        String s;
        if (adapter.getWinner() == null) {
            s = lang.getString("noWinner");
        } else {
            s = adapter.getWinner();
        }
        lblWinner = new JLabel(lang.getString("lblWinnerText") + ": " + s);
        lblWinner.setFont((Font) UIManager.get("Vote.font"));

        lblVoterName = new JLabel(lang.getString("voterName") + ": " + adapter.getOwnName());
        lblVoterName.setFont((Font) UIManager.get("Vote.font"));
    }

    private void buildLayout() {
        layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblVoterName)
                                .addComponent(lblWinner)
                                .addComponent(form)
                        )
                        .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnInfos)
                                        .addGap(0, 0, Short.MAX_VALUE)
                                )
                                .addComponent(chart, 0, 0, Short.MAX_VALUE)
                        )
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExit)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                                .addComponent(lblVoterName)
                                .addComponent(lblWinner)
                                .addComponent(form)
                                .addGap(0, 0, Short.MAX_VALUE)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btnInfos)
                                .addComponent(chart)
                        )
                )
                .addComponent(btnExit)
        );

        //layout.setHonorsVisibility(true);
        this.setLayout(layout);
    }
}
