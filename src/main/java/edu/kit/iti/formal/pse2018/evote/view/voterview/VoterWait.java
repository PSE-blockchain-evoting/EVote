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

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;

public class VoterWait extends VoterGUIPanel {

    private VoterVSComponentManager manager;

    private JButton btnExit;
    private ExtendableList form;
    private JLabel lblOwnVote;
    private JLabel lblElectionEnd;
    private JLabel lblVoterName;

    private GroupLayout layout;

    /**
     * Creates an instance of VoterWait.
     *
     * @param adapter the context of the UI.
     * @param manager The componentManager for this election.
     */
    public VoterWait(VoterAdapter adapter, VoterVSComponentManager manager) {
        super(adapter);
        this.manager = manager;

        manager.setVote(adapter.getOwnVote());
        form = manager.createVotingForm();
        manager.setEditable(false);

        initComponents();
        buildLayout();
    }

    private void initComponents() {
        ResourceBundle lang = ResourceBundle.getBundle("VoterView");
        Font f = (Font) UIManager.get("General.font");
        btnExit = new JButton(lang.getString("btnExitText"));
        btnExit.setFont(f);
        btnExit.addActionListener(adapter.getLogoutListener());

        lblOwnVote = new JLabel(lang.getString("lblOwnVoteText"));
        lblOwnVote.setFont(f);

        Date end = adapter.getElectionData().getEndDate();
        Calendar c = Calendar.getInstance(Locale.getDefault());
        c.setTime(end);
        SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
        lblElectionEnd = new JLabel(lang.getString("lblElectionEndText")
                + " " + dt.format(c.getTime()));
        lblElectionEnd.setFont(f);

        lblVoterName = new JLabel(lang.getString("voterName") + ": " + adapter.getOwnName());
        lblVoterName.setFont((Font) UIManager.get("Vote.font"));
    }

    private void buildLayout() {
        layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                        .addComponent(lblVoterName)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                )
                .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblElectionEnd)
                                .addComponent(lblOwnVote)
                                .addComponent(form)
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExit)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(lblVoterName)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                .addComponent(lblElectionEnd)
                .addGap(0, 10, 10)
                .addComponent(lblOwnVote)
                .addComponent(form)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 0, Short.MAX_VALUE)
                .addComponent(btnExit)
        );

        this.setLayout(layout);
    }
}
